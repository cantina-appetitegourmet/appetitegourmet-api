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

import br.com.appetitegourmet.api.models.AnoSerieUnidade;
import br.com.appetitegourmet.api.services.AnoSerieUnidadeService;

@RestController
@RequestMapping("/anoSerieUnidades")
public class AnoSerieUnidadeController {
    private final AnoSerieUnidadeService anoSerieUnidadeService;
    
    public AnoSerieUnidadeController(AnoSerieUnidadeService anoSerieUnidadeService) {
        this.anoSerieUnidadeService = anoSerieUnidadeService;
    }
    
    @GetMapping
    public List<AnoSerieUnidade> listarAnoSerieUnidades() {
        return anoSerieUnidadeService.listarAnoSerieUnidades();
    }
    
    @GetMapping("/anoSerie/{anoSerieId}")
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorAnoSerie(@PathVariable Long anoSerieId) {
        return anoSerieUnidadeService.listarAnoSerieUnidadesPorAnoSerie(anoSerieId);
    }
    
    @GetMapping("/unidade/{unidadeId}")
    public List<AnoSerieUnidade> listarAnoSerieUnidadesPorUnidade(@PathVariable Long unidadeId) {
        return anoSerieUnidadeService.listarAnoSerieUnidadesPorUnidade(unidadeId);
    }
    
    @GetMapping("/{id}")
    public AnoSerieUnidade buscarAnoSerieUnidadePorId(@PathVariable Long id) {
        return anoSerieUnidadeService.buscarAnoSerieUnidadePorId(id);
    }
    
    @PostMapping
    public AnoSerieUnidade salvarAnoSerieUnidade(@RequestBody AnoSerieUnidade anoSerieUnidade) {
        return anoSerieUnidadeService.salvarAnoSerieUnidade(anoSerieUnidade);
    }
    
    @DeleteMapping("/{id}")
    public void excluirAnoSerieUnidade(@PathVariable Long id) {
        anoSerieUnidadeService.excluirAnoSerieUnidade(id);
    }

    @PutMapping("/{id}")
    public void editarAnoSerieUnidade(@PathVariable Long id, @RequestBody AnoSerieUnidade anoSerieUnidade) {
        anoSerieUnidadeService.editarAnoSerieUnidade(id, anoSerieUnidade);
    }
}