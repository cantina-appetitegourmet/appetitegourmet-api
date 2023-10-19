package br.com.appetitegourmet.api.controllers;

import java.io.IOException;
import java.util.Arrays;
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

import br.com.appetitegourmet.api.dto.CadastroResponsavelComUsuarioRequest;
import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.services.EmailService;
import br.com.appetitegourmet.api.services.ResponsavelService;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.security.services.UserService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/responsaveis")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class ResponsavelController {

	private final ResponsavelService responsavelService;
	private final EmailService emailService;
	private final UserService userService;
    
    public ResponsavelController(ResponsavelService responsavelService, EmailService emailService, UserService userService) {
        this.responsavelService = responsavelService;
		this.emailService = emailService;
		this.userService = userService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<Responsavel> listarResponsaveis() {
        return responsavelService.listarResponsaveis();
    }
    
    @GetMapping("/consultaCpf/{cpf}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Boolean consultaCpf(@PathVariable String cpf) {
        return responsavelService.consultaCpf(cpf);
    }
    
    @GetMapping("/consultaEmail/{email}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Boolean consultaEmail(@PathVariable String email) {
        return responsavelService.consultaEmail(email);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Responsavel buscarResponsavelPorId(@PathVariable Long id) {
        return responsavelService.buscarResponsavelPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Responsavel salvarResponsavel(@RequestBody Responsavel responsavel) throws MessagingException, IOException {
    	Responsavel cadastrado = responsavelService.salvarResponsavel(responsavel);
        return cadastrado;
    }
    
    @PostMapping("/enviarEmail")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Responsavel enviarEmail(@RequestBody CadastroResponsavelComUsuarioRequest cadastro) throws MessagingException, IOException {
    	Responsavel responsavel = new Responsavel();
    	int resp;
    	Set<String> strRoles = Set.of("resp");
    	
    	SignupRequest signUpRequest = new SignupRequest(); 
    	
    	responsavel.setPessoa(cadastro.getPessoa());
    	
    	signUpRequest.setUsername(cadastro.getPessoa().getEmail());
    	signUpRequest.setEmail(cadastro.getPessoa().getEmail());
    	signUpRequest.setPassword(cadastro.getSenha());
    	signUpRequest.setRole(strRoles);
    	resp = userService.registerUser(signUpRequest);
    	
    	if(resp == 0) {
	    	Responsavel cadastrado = responsavelService.salvarResponsavel(responsavel);
	    	String mensagem = "Sua conta foi ativada com sucesso, faça login para fazer a adesão ao sistema!";
	    	emailService.sendHtmlEmail("ricardooliveira@dot7", 
	    							   cadastrado.getPessoa().getEmail(), 
	    							   "Ativação da conta Appetite Gourmet", mensagem, null);
	        return cadastrado;
    	}
    	return null;
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void excluirResponsavel(@PathVariable Long id) {
        responsavelService.excluirResponsavel(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void editarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        responsavelService.editarResponsavel(id, responsavel);
    }
}
