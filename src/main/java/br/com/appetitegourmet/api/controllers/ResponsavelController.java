package br.com.appetitegourmet.api.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import br.com.appetitegourmet.api.dto.ResponsavelRequest;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import br.com.appetitegourmet.api.spring.login.mapper.UserMapper;
import br.com.appetitegourmet.api.spring.login.models.Role;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.response.UserInfoResponse;
import br.com.appetitegourmet.api.spring.login.repository.RoleRepository;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;
import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
import br.com.appetitegourmet.api.services.EmailService;
import br.com.appetitegourmet.api.services.ResponsavelService;
import br.com.appetitegourmet.api.spring.login.models.ERole;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.security.services.UserService;
import jakarta.mail.MessagingException;
import utils.GeracaoSenha;

@RestController
@RequestMapping("/responsaveis")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ResponsavelController {
	private JwtUtils jwtUtils;
	UserRepository userRepository;
	RoleRepository roleRepository;
	private final PessoaMapper pessoaMapper;
	private final ResponsavelService responsavelService;
	private final EmailService emailService;
	private final UserService userService;
	private final UserMapper userMapper;
	
	@Autowired 
	private Environment env;

	@GetMapping
	@PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
	public Responsavel listarAlunosResponsavel(HttpServletRequest request) {
		String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
		Optional<User> user = userRepository.findByUsername(username);
		return user.get().getPessoa().getResponsavel();
	}

	@GetMapping("/admin")
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
    public UserInfoResponse enviarEmail(@RequestBody @NotNull ResponsavelRequest cadastro) throws MessagingException, IOException {
		if (userRepository.existsByUsername(cadastro.getPessoa().getEmail())) {
			return null;
		}
		HashSet<Role> roles = new HashSet<>();
		Role modRole = roleRepository.findByName(ERole.ROLE_RESPONSAVEL).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(modRole);
    	GeracaoSenha gs = new GeracaoSenha();
    	String password;
    	
    	SignupRequest signUpRequest = new SignupRequest();
    	
    	password = gs.GeradorSenha();
    	signUpRequest.setUsername(cadastro.getPessoa().getEmail());
    	signUpRequest.setEmail(cadastro.getPessoa().getEmail());
    	signUpRequest.setPassword(password);
		signUpRequest.setRoles(roles);
		signUpRequest.setPessoa(pessoaMapper.INSTANCE.PessoaRequestToPessoa(cadastro.getPessoa()));
    	User user = userService.registerUser(signUpRequest);
		if(userService.salvarHashSenha(cadastro.getPessoa().getEmail(), password)) {
			Responsavel responsavel = new Responsavel();
			responsavel.setPessoa(user.getPessoa());
			responsavelService.salvarResponsavel(responsavel);
			String mensagem = "Sua conta foi criada com sucesso!\n"
					+ "Usuario: " + cadastro.getPessoa().getEmail() + "\n"
					+ "Crie a senha para fazer login e adesão ao sistema!" + "\n"
					+ "Link para criação da senha: "
					+ env.getProperty("appetitegourmet.app.linkPassword") + password
					+ "/" + signUpRequest.getPessoa().getEmail() + "\n";
			emailService.sendHtmlEmail(env.getProperty("spring.mail.username"),
					signUpRequest.getPessoa().getEmail(),
					"Criação da conta Appetite Gourmet", mensagem, null);
			return userMapper.INSTANCE.userToUserInfoResponse(user);
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
