package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {
    private final UnidadeService unidadeService;
    
    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }
    
    @GetMapping
    public List<Unidade> listarUnidades() {
        return unidadeService.listarUnidades();
    }
    
    @GetMapping("/colegio/{colegioId}")
    public List<Unidade> listarUnidadesPorColegio(@PathVariable Long colegioId) {
        return unidadeService.listarUnidadesPorColegio(colegioId);
    }
    
    @GetMapping("/{id}")
    public Unidade buscarUnidadePorId(@PathVariable Long id) {
        return unidadeService.buscarUnidadePorId(id);
    }
    
    @PostMapping
    public Unidade salvarUnidade(@RequestBody Unidade unidade) {
        return unidadeService.salvarUnidade(unidade);
    }
    
    @DeleteMapping("/{id}")
    public void excluirTurma(@PathVariable Long id) {
        unidadeService.excluirUnidade(id);
    }
}