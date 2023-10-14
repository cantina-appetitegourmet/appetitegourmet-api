package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.Parentesco;
import br.com.appetitegourmet.api.services.ParentescoService;

@RestController
@RequestMapping("/parentescos")
public class ParentescoController {
    private final ParentescoService parentescoService;
    
    public ParentescoController(ParentescoService parentescoService) {
        this.parentescoService = parentescoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public List<Parentesco> listarParentescos() {
        return parentescoService.listarParentescos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Parentesco buscarParentescoPorId(@PathVariable Long id) {
        return parentescoService.buscarParentescoPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Parentesco salvarParentesco(@RequestBody Parentesco parentesco) {
        return parentescoService.salvarParentesco(parentesco);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void excluirParentesco(@PathVariable Long id) {
        parentescoService.excluirParentesco(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void editarParentesco(@PathVariable Long id, @RequestBody Parentesco parentesco) {
        parentescoService.editarParentesco(id, parentesco);
    }
}
