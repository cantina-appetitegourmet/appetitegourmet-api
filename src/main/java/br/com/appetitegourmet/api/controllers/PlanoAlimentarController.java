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

import br.com.appetitegourmet.api.models.PlanoAlimentar;
import br.com.appetitegourmet.api.services.PlanoAlimentarService;

@RestController
@RequestMapping("/planosAlimentares")
@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class PlanoAlimentarController {
    private final PlanoAlimentarService planoAlimentarService;
    
    public PlanoAlimentarController(PlanoAlimentarService planoAlimentarService) {
        this.planoAlimentarService = planoAlimentarService;
    }
    
    @GetMapping
    public List<PlanoAlimentar> listarPlanoAlimentars() {
        return planoAlimentarService.listarPlanosAlimentares();
    }
    
    @GetMapping("/{id}")
    public PlanoAlimentar buscarPlanoAlimentarPorId(@PathVariable Long id) {
        return planoAlimentarService.buscarPlanoAlimentarPorId(id);
    }
    
    @PostMapping
    public PlanoAlimentar salvarPlanoAlimentar(@RequestBody PlanoAlimentar planoAlimentar) {
        return planoAlimentarService.salvarPlanoAlimentar(planoAlimentar);
    }
    
    @DeleteMapping("/{id}")
    public void excluirPlanoAlimentar(@PathVariable Long id) {
        planoAlimentarService.excluirPlanoAlimentar(id);
    }

    @PutMapping("/{id}")
    public void editarPlanoAlimentar(@PathVariable Long id, @RequestBody PlanoAlimentar planoAlimentar) {
        planoAlimentarService.editarPlanoAlimentar(id, planoAlimentar);
    }
}
