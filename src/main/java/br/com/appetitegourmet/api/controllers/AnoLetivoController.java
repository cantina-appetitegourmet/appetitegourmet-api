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

import br.com.appetitegourmet.api.models.AnoLetivo;
import br.com.appetitegourmet.api.services.AnoLetivoService;

@RestController
@RequestMapping("/anosLetivos")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AnoLetivoController {

	private final AnoLetivoService anoLetivoService;
    
    public AnoLetivoController(AnoLetivoService anoLetivoService) {
        this.anoLetivoService = anoLetivoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<AnoLetivo> listarAnoLetivos() {
        return anoLetivoService.listarAnoLetivos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public AnoLetivo buscarAnoLetivoPorId(@PathVariable Long id) {
        return anoLetivoService.buscarAnoLetivoPorId(id);
    }
    
    @GetMapping("/ativos")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<AnoLetivo> buscarAnosLetivosAtivos() {
        return anoLetivoService.buscarAnosLetivosAtivos();
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public AnoLetivo salvarAnoLetivo(@RequestBody AnoLetivo anoLetivo) {
        return anoLetivoService.salvarAnoLetivo(anoLetivo);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirAnoLetivo(@PathVariable Long id) {
        anoLetivoService.excluirAnoLetivo(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void editarAnoLetivo(@PathVariable Long id, @RequestBody AnoLetivo anoLetivo) {
        anoLetivoService.salvarAnoLetivo(anoLetivo);
    }
}
