package br.com.appetitegourmet.api.spring.login.security.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.spring.login.models.AlterPassword;
import br.com.appetitegourmet.api.spring.login.models.ERole;
import br.com.appetitegourmet.api.spring.login.models.Role;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.LoginRequest;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.repository.AlterPasswordRepository;
import br.com.appetitegourmet.api.spring.login.repository.RoleRepository;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AlterPasswordRepository alterRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, AlterPasswordRepository alterRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.alterRepository = alterRepository;		
	}
	
	public int registerUser(SignupRequest signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return 1;
		}

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return 2;
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getUsername(),
	                         signUpRequest.getEmail(),
	                         encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_OPERADOR)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "resp":
	          Role modRole = roleRepository.findByName(ERole.ROLE_RESPONSAVEL)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_OPERADOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);
	    
	    return 0;
	}
	
	public Boolean salvarHashSenha(String email, String hash) {
		Boolean retorno = false;
		AlterPassword alter = new AlterPassword();
		
		alter.setEmail(email);
		alter.setHash(hash);
		alterRepository.save(alter);
		
		return retorno;
	}
	
	public int alterPassword(LoginRequest loginRequest) {
		Optional <User> optUser = userRepository.findByUsername(loginRequest.getUsername());
		User user;
		Boolean existe = alterRepository.existsByEmailAndHash(loginRequest.getUsername(), loginRequest.getHash());
		
		if(!existe) {
			return 1;
		}
		
		if(!optUser.isPresent()) {
			return 1;
		}
		
		user = optUser.get();
		user.setPassword(encoder.encode(loginRequest.getPassword()));
		userRepository.save(user);
		//alterRepository.deleteByEmailAndHash(loginRequest.getUsername(), loginRequest.getHash());
		
		return 0;
	}
}
