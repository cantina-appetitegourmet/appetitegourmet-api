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

import br.com.appetitegourmet.api.models.Unidade;
import br.com.appetitegourmet.api.services.UnidadeService;

@RestController
@RequestMapping("/unidades")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class UnidadeController {
    private final UnidadeService unidadeService;
    
    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<Unidade> listarUnidades() {
        return unidadeService.listarUnidades();
    }
    
    @GetMapping("/colegio/{colegioId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Unidade> listarUnidadesPorColegio(@PathVariable Long colegioId) {
        return unidadeService.listarUnidadesPorColegio(colegioId);
    }
    
    @GetMapping("/empresa/{empresaId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<Unidade> listarUnidadesPorEmpresa(@PathVariable Long empresaId) {
        return unidadeService.listarUnidadesPorEmpresa(empresaId);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Unidade buscarUnidadePorId(@PathVariable Long id) {
        return unidadeService.buscarUnidadePorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public Unidade salvarUnidade(@RequestBody Unidade unidade) {
        return unidadeService.salvarUnidade(unidade);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirUnidade(@PathVariable Long id) {
        unidadeService.excluirUnidade(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarUnidade(@PathVariable Long id, @RequestBody Unidade unidade) {
        unidadeService.editarUnidade(id, unidade);
    }
}