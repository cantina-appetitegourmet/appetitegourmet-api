package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
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
public class ContratoPlanoController {

	private final ContratoPlanoService contratoPlanoService;
    
    public ContratoPlanoController(ContratoPlanoService contratoPlanoService) {
        this.contratoPlanoService = contratoPlanoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<ContratoPlano> listarContratoPlanos() {
        return contratoPlanoService.listarContratoPlanos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public ContratoPlano buscarContratoPlanoPorId(@PathVariable Long id) {
        return contratoPlanoService.buscarContratoPlanoPorId(id);
    }
    
    @GetMapping("/contrato/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public List<ContratoPlano> buscarContratoPlanoPorContratoId(@PathVariable Long id) {
        return contratoPlanoService.buscarContratoPlanoPorContratoId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public ContratoPlano salvarContratoPlano(@RequestBody ContratoPlano contratoPlano) {
        return contratoPlanoService.salvarContratoPlano(contratoPlano);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void excluirContratoPlano(@PathVariable Long id) {
        contratoPlanoService.excluirContratoPlano(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void editarContratoPlano(@PathVariable Long id, @RequestBody ContratoPlano contratoPlano) {
        contratoPlanoService.editarContratoPlano(id, contratoPlano);
    }
}
