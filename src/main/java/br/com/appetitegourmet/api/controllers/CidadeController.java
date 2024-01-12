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

import br.com.appetitegourmet.api.models.Cidade;
import br.com.appetitegourmet.api.services.CidadeService;

@RestController
@RequestMapping("/cidades")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class CidadeController {

	private final CidadeService cidadeService;
    
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Cidade> listarCidades() {
        return cidadeService.listarCidades();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Cidade buscarCidadePorId(@PathVariable Long id) {
        return cidadeService.buscarCidadePorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Cidade salvarCidade(@RequestBody Cidade cidade) {
        return cidadeService.salvarCidade(cidade);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirCidade(@PathVariable Long id) {
        cidadeService.excluirCidade(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarCidade(@PathVariable Long id, @RequestBody Cidade cidade) {
        cidadeService.salvarCidade(cidade);
    }
}