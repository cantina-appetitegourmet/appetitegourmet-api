package br.com.appetitegourmet.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.services.UtilService;

@RestController
@RequestMapping("/utils")
public class UtilController {
	
	private final UtilService utilService;
	
	public UtilController(UtilService utilService) {
		this.utilService = utilService;
	}

	@GetMapping("/consultaEndereco/{cep}")
	@PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public Endereco consultaEnderecoPorCep(@PathVariable String cep) throws Exception {
        return utilService.consultaEnderecoPorCep(cep);
    }
}
