package br.com.appetitegourmet.api.controllers;

import java.util.List;

import br.com.appetitegourmet.api.dto.ContratoPlanoReq;
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

import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.services.ContratoPlanoService;

@RestController
@RequestMapping("/contratoPlanos")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ContratoPlanoController {

	private final ContratoPlanoService contratoPlanoService;
    
    public ContratoPlanoController(ContratoPlanoService contratoPlanoService) {
        this.contratoPlanoService = contratoPlanoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<ContratoPlano> listarContratoPlanos() {
        return contratoPlanoService.listarContratoPlanos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public ContratoPlano buscarContratoPlanoPorId(@PathVariable Long id) {
        return contratoPlanoService.buscarContratoPlanoPorId(id);
    }
    
    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<ContratoPlano> buscarContratoPlanoPorContratoId(@PathVariable Long id) {
        return contratoPlanoService.buscarContratoPlanoPorContratoId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
    public ContratoPlano salvarContratoPlano(@RequestBody ContratoPlanoReq contratoPlano) {
        return contratoPlanoService.salvarContratoPlano(contratoPlano);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirContratoPlano(@PathVariable Long id) {
        contratoPlanoService.excluirContratoPlano(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarContratoPlano(@PathVariable Long id, @RequestBody ContratoPlano contratoPlano) {
        contratoPlanoService.editarContratoPlano(id, contratoPlano);
    }
}
