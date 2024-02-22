package br.com.appetitegourmet.api.services;

import java.util.Optional;

import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;
import br.com.appetitegourmet.api.repositories.AssociacaoUsuarioRepository;
import br.com.appetitegourmet.api.spring.login.models.ERole;
import br.com.appetitegourmet.api.spring.login.models.Role;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.payload.request.UserInfoRequest;
import br.com.appetitegourmet.api.spring.login.repository.RoleRepository;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;

@Service
public class AssociacaoUsuarioService {
	@Autowired
	private JwtUtils jwtUtils;
	private final AssociacaoUsuarioRepository associacaoUsuarioRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	
	public AssociacaoUsuarioService(AssociacaoUsuarioRepository associacaoUsuarioRepository, RoleRepository roleRepository, UserRepository userRepository) {
		this.associacaoUsuarioRepository = associacaoUsuarioRepository;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		
	}
	
	public boolean associa(String email, ERole role, Long id) {
		AssociacaoUsuario associacao = new AssociacaoUsuario();
		Optional<AssociacaoUsuario> associacaoUsuarioDB = null;
		Optional<Role> roleDB = null;
		Optional<User> userDB = null;
		
		userDB = userRepository.findByUsername(email);
		roleDB = roleRepository.findByName(role);
		
		if(userDB.isPresent() && roleDB.isPresent()) {
			
			associacaoUsuarioDB = 
					associacaoUsuarioRepository.findByUsuarioIdAndRoleId(userDB.get().getId(), 
																		 roleDB.get().getId());
			if(associacaoUsuarioDB.isPresent()) {
				return false;
			}
			
			associacao.setUsuario(userDB.get());
			associacao.setRole(roleDB.get());
			associacao.setAssociado_id(id);
			
			associacaoUsuarioRepository.save(associacao);
			
			return true;
		}
		
		return false;
	}

	public AssociacaoUsuario pegaAssociacaoUsuario(UserInfoRequest usuario) {
		Optional<AssociacaoUsuario> associacaoUsuarioDB = null;
		Optional<Role> roleDB = null;

		System.out.println("usuario.getRole() = " + usuario.getRole());

		if(usuario.getRole().compareTo("ROLE_RESPONSAVEL") == 0) {
			System.out.println("RESPONSAVEL");
			roleDB = roleRepository.findByName(ERole.ROLE_RESPONSAVEL);
		} else if(usuario.getRole().compareTo("ROLE_OPERADOR") == 0) {
			System.out.println("OPERADOR");
			roleDB = roleRepository.findByName(ERole.ROLE_OPERADOR);
		} else if(usuario.getRole().compareTo("ROLE_ADMIN") == 0) {
			System.out.println("ADMIN");
			roleDB = roleRepository.findByName(ERole.ROLE_ADMIN);
		}



		if(roleDB.isPresent()) {
			System.out.println("findByUsuarioIdAndRoleId");
			associacaoUsuarioDB = associacaoUsuarioRepository.findByUsuarioIdAndRoleId(usuario.getId(), roleDB.get().getId());
			if(associacaoUsuarioDB.isPresent()) {
				System.out.println("Encontrado");
				return associacaoUsuarioDB.get();
			}
		}
		System.out.println("Retorno = NULL");
		return null;
	}

	public AssociacaoUsuario pegaAssociacaoUsuarioResponsavelJWT(HttpServletRequest request) {
		String jwt = jwtUtils.getJwtFromCookies(request);
		String username = jwtUtils.getUserNameFromJwtToken(jwt);
		Optional<User> user = userRepository.findByUsername(username);
		UserInfoRequest userInfo = new UserInfoRequest();
		userInfo.setId(user.get().getId());
		userInfo.setRole("ROLE_RESPONSAVEL");
		return this.pegaAssociacaoUsuario(userInfo);
	}
}
