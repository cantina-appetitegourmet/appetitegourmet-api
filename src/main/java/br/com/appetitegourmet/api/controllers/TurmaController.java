package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Turma;
import br.com.appetitegourmet.api.services.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService turmaService;
    
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }
    
    @GetMapping
    public List<Turma> listarTurmas() {
        return turmaService.listarTurmas();
    }
    
    @GetMapping("/anoSerieUnidadeTurno/{anoSerieUnidadeTurnoId}")
    public List<Turma> listarTurmasPorAnoSerieUnidadeTurno(@PathVariable Long anoSerieUnidadeTurnoId) {
        return turmaService.listarTurmasPorAnoSerieUnidadeTurno(anoSerieUnidadeTurnoId);
    }
    
    @GetMapping("/{id}")
    public Turma buscarTurmaPorId(@PathVariable Long id) {
        return turmaService.buscarTurmaPorId(id);
    }
    
    @PostMapping
    public Turma salvarTurma(@RequestBody Turma turma) {
        return turmaService.salvarTurma(turma);
    }
    
    @DeleteMapping("/{id}")
    public void excluirTurma(@PathVariable Long id) {
    	turmaService.excluirTurma(id);
    }

    @PutMapping("/{id}")
    public void editarTurma(@PathVariable Long id, @RequestBody Turma turma) {
    	turmaService.editarTurma(id, turma);
    }
}
