package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Colegio;
import br.com.appetitegourmet.api.services.ColegioService;

@RestController
@RequestMapping("/colegios")
public class ColegioController {
    private final ColegioService colegioService;
    
    public ColegioController(ColegioService colegioService) {
        this.colegioService = colegioService;
    }
    
    @GetMapping
    public List<Colegio> listarColegios() {
        return colegioService.listarColegios();
    }
    
    @GetMapping("/{id}")
    public Colegio buscarColegioPorId(@PathVariable Long id) {
        return colegioService.buscarColegioPorId(id);
    }
    
    @PostMapping
    public Colegio salvarColegio(@RequestBody Colegio colegio) {
        return colegioService.salvarColegio(colegio);
    }
    
    @DeleteMapping("/{id}")
    public void excluirColegio(@PathVariable Long id) {
        colegioService.excluirColegio(id);
    }
}
