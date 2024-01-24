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

import br.com.appetitegourmet.api.models.Serie;
import br.com.appetitegourmet.api.services.SerieService;

@RestController
@RequestMapping("/series")
@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class SerieController {
    private final SerieService serieService;
    
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }
    
    @GetMapping
    public List<Serie> listarSeries() {
        return serieService.listarSeries();
    }
    
    @GetMapping("/{id}")
    public Serie buscarSeriePorId(@PathVariable Long id) {
        return serieService.buscarSeriePorId(id);
    }
    
    @PostMapping
    public Serie salvarSerie(@RequestBody Serie serie) {
        return serieService.salvarSerie(serie);
    }
    
    @DeleteMapping("/{id}")
    public void excluirSerie(@PathVariable Long id) {
        serieService.excluirSerie(id);
    }

    @PutMapping("/{id}")
    public void editarSerie(@PathVariable Long id, @RequestBody Serie serie) {
        serieService.editarSerie(id, serie);
    }
}
