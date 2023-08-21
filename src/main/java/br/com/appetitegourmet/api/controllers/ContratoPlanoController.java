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

import br.com.appetitegourmet.api.models.ContratoPlano;
import br.com.appetitegourmet.api.services.ContratoPlanoService;

@RestController
@RequestMapping("/contratoPlanos")
public class ContratoPlanoController {

	private final ContratoPlanoService contratoPlanoService;
    
    public ContratoPlanoController(ContratoPlanoService contratoPlanoService) {
        this.contratoPlanoService = contratoPlanoService;
    }
    
    @GetMapping
    public List<ContratoPlano> listarContratoPlanos() {
        return contratoPlanoService.listarContratoPlanos();
    }
    
    @GetMapping("/{id}")
    public ContratoPlano buscarContratoPlanoPorId(@PathVariable Long id) {
        return contratoPlanoService.buscarContratoPlanoPorId(id);
    }
    
    @GetMapping("/listaPorResponsavel/{id}")
    public List<ContratoPlano> buscarContratoPlanoPorResponsavelId(Long id) {
    	return contratoPlanoService.buscarContratoPlanoPorResponsavelId(id);
    }
    
    @PostMapping
    public ContratoPlano salvarContratoPlano(@RequestBody ContratoPlano contratoPlano) {
        return contratoPlanoService.salvarContratoPlano(contratoPlano);
    }
    
    @DeleteMapping("/{id}")
    public void excluirContratoPlano(@PathVariable Long id) {
        contratoPlanoService.excluirContratoPlano(id);
    }

    @PutMapping("/{id}")
    public void editarContratoPlano(@PathVariable Long id, @RequestBody ContratoPlano contratoPlano) {
        contratoPlanoService.editarContratoPlano(id, contratoPlano);
    }
}
