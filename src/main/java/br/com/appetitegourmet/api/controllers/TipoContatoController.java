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

import br.com.appetitegourmet.api.models.TipoContato;
import br.com.appetitegourmet.api.services.TipoContatoService;

@RestController
@RequestMapping("/tiposcontatos")
public class TipoContatoController {
    private final TipoContatoService tipoContatoService;
    
    public TipoContatoController(TipoContatoService tipoContatoService) {
        this.tipoContatoService = tipoContatoService;
    }
    
    @GetMapping
    public List<TipoContato> listarTipoContatos() {
        return tipoContatoService.listarTipoContatos();
    }
    
    @GetMapping("/{id}")
    public TipoContato buscarTipoContatoPorId(@PathVariable Long id) {
        return tipoContatoService.buscarTipoContatoPorId(id);
    }
    
    @PostMapping
    public TipoContato salvarTipoContato(@RequestBody TipoContato tipoContato) {
        return tipoContatoService.salvarTipoContato(tipoContato);
    }
    
    @DeleteMapping("/{id}")
    public void excluirTipoContato(@PathVariable Long id) {
        tipoContatoService.excluirTipoContato(id);
    }

    @PutMapping("/{id}")
    public void editarTipoContato(@PathVariable Long id, @RequestBody TipoContato tipoContato) {
        tipoContatoService.editarTipoContato(id, tipoContato);
    }
}
