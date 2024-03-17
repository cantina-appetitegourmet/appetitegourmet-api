package br.com.appetitegourmet.api.spring.login.controllers;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import br.com.appetitegourmet.api.spring.login.mapper.UserDetailsMapper;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.LostPasswordRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import utils.GeracaoSenha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.services.EmailService;
import br.com.appetitegourmet.api.spring.login.payload.request.LoginRequest;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.payload.response.UserInfoResponse;
import br.com.appetitegourmet.api.spring.login.payload.response.MessageResponse;
import br.com.appetitegourmet.api.spring.login.repository.RoleRepository;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;
import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import br.com.appetitegourmet.api.spring.login.security.services.UserDetailsImpl;
import br.com.appetitegourmet.api.spring.login.security.services.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final Environment env;
    private final EmailService emailService;

  @PostMapping("/signin")
  public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    System.out.println("userDetails=" + userDetails.toString());
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(userDetailsMapper.INSTANCE.UserDetailsImplToUserInfoResponse(userDetails));
  }

  /*@PostMapping("/signup")
  public ResponseEntity<?> registerUserResponsavel(@Valid @RequestBody SignupRequest signUpRequest) {
	Set<String> roles = new HashSet<>(Arrays.asList("resp"));

	signUpRequest.setRole(roles);
	int resp = userService.registerUser(signUpRequest);
	
    if (resp == 1) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (resp == 2) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @PostMapping("/signupAdmin")
  public ResponseEntity<?> registerUserAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
	Set<String> roles = new HashSet<>(Arrays.asList("admin"));
	  
	signUpRequest.setRole(roles);
	int resp = userService.registerUser(signUpRequest);
	
    if (resp == 1) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (resp == 2) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
  
  @PostMapping("/signupOperador")
  public ResponseEntity<?> registerUserOperador(@Valid @RequestBody SignupRequest signUpRequest) {
	Set<String> roles = new HashSet<>(Arrays.asList("oper"));
	  
	signUpRequest.setRole(roles);
	int resp = userService.registerUser(signUpRequest);
	
    if (resp == 1) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (resp == 2) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }*/
  
  @PostMapping("/alterPassword")
  public ResponseEntity<?> alterPassword(@Valid @RequestBody LoginRequest loginRequest) {
	int resp = userService.alterPassword(loginRequest);
	
    if (resp == 1) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Falha ao alterar a senha!"));
    }

    return ResponseEntity.ok(new MessageResponse("Senha alterada com sucesso!"));
  }
  
  @PostMapping("/lostPassword")
  public ResponseEntity<?> lostPassword(@Valid @RequestBody LostPasswordRequest lostPasswordRequest) {
	  
	GeracaoSenha gs = new GeracaoSenha();
	String password;
	Boolean falhou = false;
	
	if(!userRepository.existsByEmail(lostPasswordRequest.getUsername())) {
		return ResponseEntity.badRequest().body(new MessageResponse("Usuário inexistente!"));
	}
	
	password = gs.GeradorSenha();
	userService.removeLink(lostPasswordRequest.getUsername());
	userService.salvarHashSenha(lostPasswordRequest.getUsername(), password);
	
	String mensagem = "A alteração de senha da sua conta foi solicitada com sucesso!\n"
			+ "Usuario: " + lostPasswordRequest.getUsername() + "\n"
			+ "Altere a senha para voltar a fazer login no sistema!" + "\n"
			+ "Link para alteração da senha: " 
			+ env.getProperty("appetitegourmet.app.linkPassword") + password 
			+ "/" + lostPasswordRequest.getUsername() + "\n";
	try {
		emailService.sendHtmlEmail(env.getProperty("spring.mail.username"), lostPasswordRequest.getUsername(),
								   "Alteração de senha da conta Appetite Gourmet", mensagem, null);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		falhou = true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		falhou = true;
	}
	  
	if (falhou) {
		return ResponseEntity.badRequest().body(new MessageResponse("Solicitação de alteração de senha realizada sem sucesso!"));
	} 
	
    return ResponseEntity.ok(new MessageResponse("Solicitação de alteração de senha realizada com sucesso!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}