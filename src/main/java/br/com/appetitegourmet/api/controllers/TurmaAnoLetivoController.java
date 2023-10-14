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

import br.com.appetitegourmet.api.models.TurmaAnoLetivo;
import br.com.appetitegourmet.api.services.TurmaAnoLetivoService;

@RestController
@RequestMapping("/turmaAnoLetivos")
public class TurmaAnoLetivoController {
    private final TurmaAnoLetivoService turmaAnoLetivoService;
    
    public TurmaAnoLetivoController(TurmaAnoLetivoService turmaAnoLetivoService) {
        this.turmaAnoLetivoService = turmaAnoLetivoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<TurmaAnoLetivo> listarTurmaAnoLetivos() {
        return turmaAnoLetivoService.listarTurmaAnoLetivos();
    }
    
    @GetMapping("/turma/{turmaId}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorTurma(@PathVariable Long turmaId) {
        return turmaAnoLetivoService.listarTurmaAnoLetivosPorTurma(turmaId);
    }
    
    @GetMapping("/anoLetivo/{anoLetivoId}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorAnoLetivo(@PathVariable Long anoLetivoId) {
        return turmaAnoLetivoService.listarTurmaAnoLetivosPorAnoLetivo(anoLetivoId);
    }
    
    @GetMapping("/anoSerieUnidade/{unidadeId}/anoLetivo/{anoLetivoId}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public List<TurmaAnoLetivo> listarTurmaAnoLetivosPorUnidadeEAnoLetivo(@PathVariable Long unidadeId, @PathVariable Long anoLetivoId) {
        return turmaAnoLetivoService.listarTurmaAnoLetivosPorUnidadeEAnoLetivo(unidadeId, anoLetivoId);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public TurmaAnoLetivo buscarTurmaAnoLetivoPorId(@PathVariable Long id) {
        return turmaAnoLetivoService.buscarTurmaAnoLetivoPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public TurmaAnoLetivo salvarTurmaAnoLetivo(@RequestBody TurmaAnoLetivo turmaAnoLetivo) {
        return turmaAnoLetivoService.salvarTurmaAnoLetivo(turmaAnoLetivo);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void excluirTurmaAnoLetivo(@PathVariable Long id) {
        turmaAnoLetivoService.excluirTurmaAnoLetivo(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void editarTurmaAnoLetivo(@PathVariable Long id, @RequestBody TurmaAnoLetivo turmaAnoLetivo) {
        turmaAnoLetivoService.editarTurmaAnoLetivo(id, turmaAnoLetivo);
    }
}
