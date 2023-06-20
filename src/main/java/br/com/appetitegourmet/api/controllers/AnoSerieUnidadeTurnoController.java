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

import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;
import br.com.appetitegourmet.api.services.AnoSerieUnidadeTurnoService;

@RestController
@RequestMapping("/anoSerieUnidadeTurnos")
public class AnoSerieUnidadeTurnoController {
    private final AnoSerieUnidadeTurnoService anoSerieUnidadeTurnoService;
    
    public AnoSerieUnidadeTurnoController(AnoSerieUnidadeTurnoService anoSerieUnidadeTurnoService) {
        this.anoSerieUnidadeTurnoService = anoSerieUnidadeTurnoService;
    }
    
    @GetMapping
    public List<AnoSerieUnidadeTurno> listarAnoSeries() {
        return anoSerieUnidadeTurnoService.listarAnoSeries();
    }
    
    @GetMapping("/serie/{serieId}")
    public List<AnoSerieUnidadeTurno> listarAnoSeriesPorSerie(@PathVariable Long serieId) {
        return anoSerieUnidadeTurnoService.listarAnoSeriesPorSerie(serieId);
    }
    
    @GetMapping("/{id}")
    public AnoSerieUnidadeTurno buscarAnoSeriePorId(@PathVariable Long id) {
        return anoSerieUnidadeTurnoService.buscarAnoSeriePorId(id);
    }
    
    @PostMapping
    public AnoSerieUnidadeTurno salvarAnoSerie(@RequestBody AnoSerieUnidadeTurno anoSerie) {
        return anoSerieUnidadeTurnoService.salvarAnoSerie(anoSerie);
    }
    
    @DeleteMapping("/{id}")
    public void excluirAnoSerie(@PathVariable Long id) {
    	anoSerieUnidadeTurnoService.excluirAnoSerie(id);
    }

    @PutMapping("/{id}")
    public void editarAnoSerie(@PathVariable Long id, @RequestBody AnoSerieUnidadeTurno anoSerie) {
    	anoSerieUnidadeTurnoService.editarAnoSerie(id, anoSerie);
    }
}
