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

import br.com.appetitegourmet.api.models.Responsavel;
import br.com.appetitegourmet.api.services.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

	private final ResponsavelService responsavelService;
    
    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }
    
    @GetMapping
    public List<Responsavel> listarResponsaveis() {
        return responsavelService.listarResponsaveis();
    }
    
    @GetMapping("/{id}")
    public Responsavel buscarResponsavelPorId(@PathVariable Long id) {
        return responsavelService.buscarResponsavelPorId(id);
    }
    
    @PostMapping
    public Responsavel salvarResponsavel(@RequestBody Responsavel responsavel) {
        return responsavelService.salvarResponsavel(responsavel);
    }
    
    @DeleteMapping("/{id}")
    public void excluirResponsavel(@PathVariable Long id) {
        responsavelService.excluirResponsavel(id);
    }

    @PutMapping("/{id}")
    public void editarResponsavel(@PathVariable Long id, @RequestBody Responsavel responsavel) {
        responsavelService.editarResponsavel(id, responsavel);
    }
}
