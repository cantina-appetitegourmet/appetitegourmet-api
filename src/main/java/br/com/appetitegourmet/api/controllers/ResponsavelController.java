package br.com.appetitegourmet.api.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.services.AssociacaoUsuarioService;
import br.com.appetitegourmet.api.services.EmailService;
import br.com.appetitegourmet.api.services.ResponsavelService;
import br.com.appetitegourmet.api.spring.login.models.ERole;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.security.services.UserService;
import jakarta.mail.MessagingException;
import utils.GeracaoSenha;

@RestController
@RequestMapping("/responsaveis")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ResponsavelController {

	private final ResponsavelService responsavelService;
	private final EmailService emailService;
	private final UserService userService;
	private final AssociacaoUsuarioService associacaoService;
	
	@Autowired 
	private Environment env;
    
    public ResponsavelController(ResponsavelService responsavelService, EmailService emailService, UserService userService, AssociacaoUsuarioService associacaoService) {
        this.responsavelService = responsavelService;
		this.emailService = emailService;
		this.userService = userService;
		this.associacaoService = associacaoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Responsavel> listarResponsaveis() {
        return responsavelService.listarResponsaveis();
    }
    
    @GetMapping("/consultaCpf/{cpf}")
    //@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public Boolean consultaCpf(@PathVariable String cpf) {
        return responsavelService.consultaCpf(cpf);
    }
    
    @GetMapping("/consultaEmail/{email}")
    //@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public Boolean consultaEmail(@PathVariable String email) {
        return responsavelService.consultaEmail(email);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Responsavel buscarResponsavelPorId(@PathVariable Long id) {
        return responsavelService.buscarResponsavelPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Responsavel salvarResponsavel(@RequestBody Responsavel responsavel) throws MessagingException, IOException {
    	Responsavel cadastrado = responsavelService.salvarResponsavel(responsavel);
        return cadastrado;
    }
    
    @PostMapping("/enviarEmail")
    public Responsavel enviarEmail(@RequestBody Responsavel cadastro) throws MessagingException, IOException {
    	Responsavel responsavel = new Responsavel();
    	int resp;
    	Set<String> strRoles = Set.of("resp");
    	GeracaoSenha gs = new GeracaoSenha();
    	String password;
    	
    	SignupRequest signUpRequest = new SignupRequest(); 
    	
    	responsavel.setPessoa(cadastro.getPessoa());
    	
    	password = gs.GeradorSenha();
    	signUpRequest.setUsername(cadastro.getPessoa().getEmail());
    	signUpRequest.setEmail(cadastro.getPessoa().getEmail());
    	signUpRequest.setPassword(password);
    	signUpRequest.setRole(strRoles);
    	resp = userService.registerUser(signUpRequest);
    	
    	if(resp == 0) {
    		
    		if(userService.salvarHashSenha(cadastro.getPessoa().getEmail(), password)) {
    		
		    	Responsavel cadastrado = responsavelService.salvarResponsavel(responsavel);
		    	
		    	associacaoService.associa(cadastro.getPessoa().getEmail(), 
		    							  ERole.ROLE_RESPONSAVEL, 
		    							  cadastrado.getId());
		    	
		    	String mensagem = "Sua conta foi criada com sucesso!\n"
		    			+ "Usuario: " + cadastro.getPessoa().getEmail() + "\n" 
		    			+ "Crie a senha para fazer login e adesão ao sistema!" + "\n"
		    			+ "Link para criação da senha: " 
		    			+ env.getProperty("appetitegourmet.app.linkPassword") + password 
		    			+ "/" + cadastrado.getPessoa().getEmail() + "\n";
		    	emailService.sendHtmlEmail(env.getProperty("spring.mail.username"), 
		    							   cadastrado.getPessoa().getEmail(), 
		    							   "Criação da conta Appetite Gourmet", mensagem, null);
		        return cadastrado;
    		}
    	}
    	return null;
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirResponsavel(@PathVariable Long id) {
        responsavelService.excluirResponsavel(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        responsavelService.editarResponsavel(id, responsavel);
    }
}
