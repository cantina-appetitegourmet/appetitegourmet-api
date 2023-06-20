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

import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import br.com.appetitegourmet.api.services.PlanoAlimentarPrecoService;

@RestController
@RequestMapping("/planoAlimentarPrecos")
public class PlanoAlimentarPrecoController {
    private final PlanoAlimentarPrecoService planoAlimentarPrecoService;
    
    public PlanoAlimentarPrecoController(PlanoAlimentarPrecoService planoAlimentarPrecoService) {
        this.planoAlimentarPrecoService = planoAlimentarPrecoService;
    }
    
    @GetMapping
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecos() {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecos();
    }
    
    @GetMapping("/planoAlimentar/{planoAlimentarId}")
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorPlanoAlimentar(@PathVariable Long planoAlimentarId) {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecosPorPlanoAlimentar(planoAlimentarId);
    }
    
    @GetMapping("/turmaAnoLetivo/{turmaAnoLetivoId}")
    public List<PlanoAlimentarPreco> listarPlanoAlimentarPrecosPorTurmaAnoLetivo(@PathVariable Long anoLetivoId) {
        return planoAlimentarPrecoService.listarPlanoAlimentarPrecosPorTurmaAnoLetivo(anoLetivoId);
    }
    
    @GetMapping("/{id}")
    public PlanoAlimentarPreco buscarPlanoAlimentarPrecoPorId(@PathVariable Long id) {
        return planoAlimentarPrecoService.buscarPlanoAlimentarPrecoPorId(id);
    }
    
    @PostMapping
    public PlanoAlimentarPreco salvarPlanoAlimentarPreco(@RequestBody PlanoAlimentarPreco planoAlimentarPreco) {
        return planoAlimentarPrecoService.salvarPlanoAlimentarPreco(planoAlimentarPreco);
    }
    
    @DeleteMapping("/{id}")
    public void excluirPlanoAlimentarPreco(@PathVariable Long id) {
        planoAlimentarPrecoService.excluirPlanoAlimentarPreco(id);
    }

    @PutMapping("/{id}")
    public void editarPlanoAlimentarPreco(@PathVariable Long id, @RequestBody PlanoAlimentarPreco planoAlimentarPreco) {
        planoAlimentarPrecoService.editarPlanoAlimentarPreco(id, planoAlimentarPreco);
    }
}
