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

import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import br.com.appetitegourmet.api.services.PlanoAlimentarPrecoService;

@RestController
@RequestMapping("/planoAlimentarPrecos")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class PlanoAlimentarPrecoController {
    private final PlanoAlimentarPrecoService planoAlimentarPrecoService;
    
    public PlanoAlimentarPrecoController(PlanoAlimentarPrecoService planoAlimentarPrecoService) {
        this.planoAlimentarPrecoService = planoAlimentarPrecoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecos() {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecos();
    }
    
    @GetMapping("/planoAlimentar/{planoAlimentarId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorPlanoAlimentar(@PathVariable Long planoAlimentarId) {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecosPorPlanoAlimentar(planoAlimentarId);
    }
    
    @GetMapping("/turmaAnoLetivo/{turmaAnoLetivoId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorTurmaAnoLetivo(@PathVariable Long turmaAnoLetivoId) {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecosPorTurmaAnoLetivo(turmaAnoLetivoId);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public PlanoAlimentarPreco buscarPlanoAlimentarPrecoPorId(@PathVariable Long id) {
        return planoAlimentarPrecoService.buscarPlanoAlimentarPrecoPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public PlanoAlimentarPreco salvarPlanoAlimentarPreco(@RequestBody PlanoAlimentarPreco planoAlimentarPreco) {
        return planoAlimentarPrecoService.salvarPlanoAlimentarPreco(planoAlimentarPreco);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirPlanoAlimentarPreco(@PathVariable Long id) {
        planoAlimentarPrecoService.excluirPlanoAlimentarPreco(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarPlanoAlimentarPreco(@PathVariable Long id, @RequestBody PlanoAlimentarPreco planoAlimentarPreco) {
        planoAlimentarPrecoService.editarPlanoAlimentarPreco(id, planoAlimentarPreco);
    }
}
