package br.com.appetitegourmet.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;
import br.com.appetitegourmet.api.services.AssociacaoUsuarioService;
import br.com.appetitegourmet.api.spring.login.payload.request.UserInfoRequest;
import br.com.appetitegourmet.api.spring.login.payload.response.MessageResponse;
import br.com.appetitegourmet.api.dto.AssociacaoResponse;

@RestController
@RequestMapping("/associacaoUsuarios")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AssociacaoUsuarioController {
	
	private final AssociacaoUsuarioService associacaoUsuarioService; 
	
	public AssociacaoUsuarioController(AssociacaoUsuarioService associacaoUsuarioService) {
		this.associacaoUsuarioService = associacaoUsuarioService;
		
	}
	
	@GetMapping("/id/{usuario}/role/{role}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public ResponseEntity<?> buscarAssociacaoUsuarioPorUsuario(@PathVariable Long usuario, 
    												 @PathVariable String role) {
		AssociacaoResponse resposta = new AssociacaoResponse(); 
		UserInfoRequest user = new UserInfoRequest();
		System.out.println("Chamando pegaAssociacaoUsuario => usuario(" + usuario.toString() + "), role=(" + role + ")");
		user.setId(usuario);
		user.setRole(role);
		AssociacaoUsuario associacaoUsuario = associacaoUsuarioService.pegaAssociacaoUsuario(user);
		
		if(associacaoUsuario == null) {
			System.out.println("Associacao NULL");
			resposta.setId((long) 0);
			return ResponseEntity.badRequest().body(new MessageResponse("Usuario não encontrado"));
		}
		// apenas o id
		System.out.println("Pegando o associado = " + associacaoUsuario.getAssociado_id().toString());
		resposta.setId(associacaoUsuario.getAssociado_id());
		
		return ResponseEntity.ok().body(resposta);
    }

}
