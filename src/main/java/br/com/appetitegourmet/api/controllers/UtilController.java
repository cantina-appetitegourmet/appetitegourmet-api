package br.com.appetitegourmet.api.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.services.UtilService;

@RestController
@RequestMapping("/utils")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net", maxAge = 3600, allowCredentials="true")
public class UtilController {
	
	private final UtilService utilService;
	
	public UtilController(UtilService utilService) {
		this.utilService = utilService;
	}

	@GetMapping("/consultaEndereco/{cep}")
    public Endereco consultaEnderecoPorCep(@PathVariable String cep) throws Exception {
        return utilService.consultaEnderecoPorCep(cep);
    }
	
	@PostMapping("/teste")
	//@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
	@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
	public Endereco Teste() throws Exception {
		System.out.println("TESTOU!!");
		return utilService.consultaEnderecoPorCep("50060060");
	}
}
