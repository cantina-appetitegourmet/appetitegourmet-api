package br.com.appetitegourmet.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.services.UtilService;

@RestController
@RequestMapping("/utils")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
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
