package br.com.appetitegourmet.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;
import br.com.appetitegourmet.api.repositories.AssociacaoUsuarioRepository;
import br.com.appetitegourmet.api.spring.login.models.ERole;
import br.com.appetitegourmet.api.spring.login.models.Role;
import br.com.appetitegourmet.api.spring.login.payload.request.UserInfoRequest;
import br.com.appetitegourmet.api.spring.login.repository.RoleRepository;

@Service
public class AssociacaoUsuarioService {
	
	private final AssociacaoUsuarioRepository associacaoUsuarioRepository;
	private final RoleRepository roleRepository;
	
	public AssociacaoUsuarioService(AssociacaoUsuarioRepository associacaoUsuarioRepository, RoleRepository roleRepository) {
		this.associacaoUsuarioRepository = associacaoUsuarioRepository;
		this.roleRepository = roleRepository;
		
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
}
