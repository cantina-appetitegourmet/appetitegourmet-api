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

import br.com.appetitegourmet.api.models.AnoSerieUnidade;
import br.com.appetitegourmet.api.services.AnoSerieUnidadeService;

@RestController
@RequestMapping("/anoSerieUnidades")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class AnoSerieUnidadeController {
    private final AnoSerieUnidadeService anoSerieUnidadeService;
    
    public AnoSerieUnidadeController(AnoSerieUnidadeService anoSerieUnidadeService) {
        this.anoSerieUnidadeService = anoSerieUnidadeService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<AnoSerieUnidade> listarAnoSerieUnidades() {
        return anoSerieUnidadeService.listarAnoSerieUnidades();
    }
    
    @GetMapping("/anoSerie/{anoSerieId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorAnoSerie(@PathVariable Long anoSerieId) {
        return anoSerieUnidadeService.listarAnoSerieUnidadesPorAnoSerie(anoSerieId);
    }
    
    @GetMapping("/unidade/{unidadeId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorUnidade(@PathVariable Long unidadeId) {
        return anoSerieUnidadeService.listarAnoSerieUnidadesPorUnidade(unidadeId);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public AnoSerieUnidade buscarAnoSerieUnidadePorId(@PathVariable Long id) {
        return anoSerieUnidadeService.buscarAnoSerieUnidadePorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public AnoSerieUnidade salvarAnoSerieUnidade(@RequestBody AnoSerieUnidade anoSerieUnidade) {
        return anoSerieUnidadeService.salvarAnoSerieUnidade(anoSerieUnidade);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirAnoSerieUnidade(@PathVariable Long id) {
        anoSerieUnidadeService.excluirAnoSerieUnidade(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarAnoSerieUnidade(@PathVariable Long id, @RequestBody AnoSerieUnidade anoSerieUnidade) {
        anoSerieUnidadeService.editarAnoSerieUnidade(id, anoSerieUnidade);
    }
}