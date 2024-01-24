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

import br.com.appetitegourmet.api.models.Colegio;
import br.com.appetitegourmet.api.services.ColegioService;

@RestController
@RequestMapping("/colegios")
@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
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

    @PutMapping("/{id}")
    public void editarColegio(@PathVariable Long id, @RequestBody Colegio colegio) {
        colegioService.editarColegio(id, colegio);
    }
}
