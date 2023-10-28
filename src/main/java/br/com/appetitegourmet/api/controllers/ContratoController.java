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

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.services.ContratoService;

@RestController
@RequestMapping("/contratos")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net", maxAge = 3600, allowCredentials="true")
public class ContratoController {

	private final ContratoService contratoService;
    
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Contrato> listarContratos() {
        return contratoService.listarContratos();
    }
    
    @GetMapping("/responsavel/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<Contrato> listarContratosDeUmResponsavel(@PathVariable Long id) {
        return contratoService.listarContratosDeUmResponsavel(id);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Contrato buscarContratoPorId(@PathVariable Long id) {
        return contratoService.buscarContratoPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public Contrato salvarContrato(@RequestBody Contrato contrato) {
        return contratoService.salvarContrato(contrato);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirContrato(@PathVariable Long id) {
        contratoService.excluirContrato(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarContrato(@PathVariable Long id, @RequestBody Contrato contrato) {
        contratoService.editarContrato(id, contrato);
    }
}
