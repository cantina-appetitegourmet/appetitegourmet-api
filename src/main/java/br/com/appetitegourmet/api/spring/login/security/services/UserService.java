package br.com.appetitegourmet.api.spring.login.security.services;

import java.util.Optional;

import br.com.appetitegourmet.api.spring.login.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.appetitegourmet.api.spring.login.models.AlterPassword;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.LoginRequest;
import br.com.appetitegourmet.api.spring.login.payload.request.SignupRequest;
import br.com.appetitegourmet.api.spring.login.repository.AlterPasswordRepository;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final AlterPasswordRepository alterRepository;
	private final UserMapper userMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	public User registerUser(SignupRequest signUpRequest) {
	    return userRepository.save(userMapper.INSTANCE.signupRequestToUserMapper(signUpRequest));
	}
	
	@Transactional
	public void removeLink(String email) {
		alterRepository.deleteByEmail(email);
	}
	
	public Boolean salvarHashSenha(String email, String hash) {
		Boolean retorno = true;
		AlterPassword alter = new AlterPassword();
		
		alter.setEmail(email);
		alter.setHash(hash);
		alterRepository.save(alter);
		
		return retorno;
	}
	
	@Transactional
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
		alterRepository.deleteByEmailAndHash(loginRequest.getUsername(), loginRequest.getHash());
		
		return 0;
	}
}
