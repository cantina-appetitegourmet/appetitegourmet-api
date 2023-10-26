package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.ContratoDesconto;
import br.com.appetitegourmet.api.services.ContratoDescontoService;

@RestController
@RequestMapping("/contratoDescontos")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class ContratoDescontoController {

	private final ContratoDescontoService contratoDescontoService;
    
    public ContratoDescontoController(ContratoDescontoService contratoDescontoService) {
		this.contratoDescontoService = contratoDescontoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<ContratoDesconto> listarContratoDescontos() {
        return contratoDescontoService.listarContratoDescontos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public ContratoDesconto buscarContratoDescontoPorId(@PathVariable Long id) {
        return contratoDescontoService.buscarContratoDescontoPorId(id);
    }
    
    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<ContratoDesconto> buscarContratoDescontoPorContratoIds(@PathVariable Long id) {
        return contratoDescontoService.buscarContratoDescontoPorContratoId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public ContratoDesconto salvarContratoDesconto(@RequestBody ContratoDesconto contratoDesconto) {
        return contratoDescontoService.salvarContratoDesconto(contratoDesconto);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirContratoDesconto(@PathVariable Long id) {
    	contratoDescontoService.excluirContratoDesconto(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarContratoDesconto(@PathVariable Long id, @RequestBody ContratoDesconto contratoDesconto) {
    	contratoDescontoService.editarContratoDesconto(id, contratoDesconto);
    }
}
