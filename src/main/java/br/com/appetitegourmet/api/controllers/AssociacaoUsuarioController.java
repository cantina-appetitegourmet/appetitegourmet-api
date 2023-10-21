package br.com.appetitegourmet.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.appetitegourmet.api.models.AssociacaoUsuario;
import br.com.appetitegourmet.api.services.AssociacaoUsuarioService;
import br.com.appetitegourmet.api.spring.login.payload.request.UserInfoRequest;
import br.com.appetitegourmet.api.dto.AssociacaoResponse;

@Controller
@RequestMapping("/associacaoUsuarios")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AssociacaoUsuarioController {
	
	private final AssociacaoUsuarioService associacaoUsuarioService; 
	
	public AssociacaoUsuarioController(AssociacaoUsuarioService associacaoUsuarioService) {
		this.associacaoUsuarioService = associacaoUsuarioService;
		
	}
	
	@GetMapping("/id/{usuario}/role/{role}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public AssociacaoResponse buscarAssociacaoUsuarioPorUsuario(@PathVariable Long usuario, 
    												 @PathVariable String role) {
		AssociacaoResponse resposta = new AssociacaoResponse(); 
		UserInfoRequest user = new UserInfoRequest();
		System.out.println("Chamando pegaAssociacaoUsuario => usuario(" + usuario.toString() + "), role=(" + role + ")");
		user.setId(usuario);
		user.setRole(role);
		AssociacaoUsuario associacaoUsuario = associacaoUsuarioService.pegaAssociacaoUsuario(user);
		
		if(associacaoUsuario == null) {
			System.out.println("Associacao NULL");
			resposta.setId(0);
			return resposta;
		}
		// apenas o id
		System.out.println("Pegando o associado = " + associacaoUsuario.getAssociado_id().toString());
		resposta.setId(associacaoUsuario.getAssociado_id());
		return resposta;
    }

}
