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

import br.com.appetitegourmet.api.models.AnoSerie;
import br.com.appetitegourmet.api.services.AnoSerieService;

@RestController
@RequestMapping("/anoSeries")
@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net", maxAge = 3600, allowCredentials="true")
public class AnoSerieController {
    private final AnoSerieService anoSerieService;
    
    public AnoSerieController(AnoSerieService anoSerieService) {
        this.anoSerieService = anoSerieService;
    }
    
    @GetMapping
    public List<AnoSerie> listarAnoSeries() {
        return anoSerieService.listarAnoSeries();
    }
    
    @GetMapping("/serie/{serieId}")
    public List<AnoSerie> listarAnoSeriesPorSerie(@PathVariable Long serieId) {
        return anoSerieService.listarAnoSeriesPorSerie(serieId);
    }
    
    @GetMapping("/{id}")
    public AnoSerie buscarAnoSeriePorId(@PathVariable Long id) {
        return anoSerieService.buscarAnoSeriePorId(id);
    }
    
    @PostMapping
    public AnoSerie salvarAnoSerie(@RequestBody AnoSerie anoSerie) {
        return anoSerieService.salvarAnoSerie(anoSerie);
    }
    
    @DeleteMapping("/{id}")
    public void excluirAnoSerie(@PathVariable Long id) {
        anoSerieService.excluirAnoSerie(id);
    }

    @PutMapping("/{id}")
    public void editarAnoSerie(@PathVariable Long id, @RequestBody AnoSerie anoSerie) {
        anoSerieService.editarAnoSerie(id, anoSerie);
    }
}