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

import br.com.appetitegourmet.api.models.AnoSerieUnidadeTurno;
import br.com.appetitegourmet.api.services.AnoSerieUnidadeTurnoService;

@RestController
@RequestMapping("/anoSerieUnidadeTurnos")
@PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class AnoSerieUnidadeTurnoController {
    private final AnoSerieUnidadeTurnoService anoSerieUnidadeTurnoService;
    
    public AnoSerieUnidadeTurnoController(AnoSerieUnidadeTurnoService anoSerieUnidadeTurnoService) {
        this.anoSerieUnidadeTurnoService = anoSerieUnidadeTurnoService;
    }
    
    @GetMapping
    public List<AnoSerieUnidadeTurno> listarAnoSerieUnidadeTurnos() {
        return anoSerieUnidadeTurnoService.listarAnoSerieUnidadeTurnos();
    }
    
    @GetMapping("/anoSerieUnidade/{anoSerieUnidadeId}")
    public List<AnoSerieUnidadeTurno> listarAnoSerieUnidadeTurnosPorAnoSerieUnidade(@PathVariable Long anoSerieUnidadeId) {
        return anoSerieUnidadeTurnoService.listarAnoSerieUnidadeTurnosPorAnoSerieUnidade(anoSerieUnidadeId);
    }
    
    @GetMapping("/{id}")
    public AnoSerieUnidadeTurno buscarAnoSerieUnidadeTurnoPorId(@PathVariable Long id) {
        return anoSerieUnidadeTurnoService.buscarAnoSerieUnidadeTurnoPorId(id);
    }
    
    @PostMapping
    public AnoSerieUnidadeTurno salvarAnoSerieUnidadeTurno(@RequestBody AnoSerieUnidadeTurno anoSerieUnidadeTurno) {
        return anoSerieUnidadeTurnoService.salvarAnoSerieUnidadeTurno(anoSerieUnidadeTurno);
    }
    
    @DeleteMapping("/{id}")
    public void excluirAnoSerieUnidadeTurno(@PathVariable Long id) {
    	anoSerieUnidadeTurnoService.excluirAnoSerieUnidadeTurno(id);
    }

    @PutMapping("/{id}")
    public void editarAnoSerieUnidadeTurno(@PathVariable Long id, @RequestBody AnoSerieUnidadeTurno anoSerieUnidadeTurno) {
    	anoSerieUnidadeTurnoService.editarAnoSerieUnidadeTurno(id, anoSerieUnidadeTurno);
    }
}
