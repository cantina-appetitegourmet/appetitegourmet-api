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

import br.com.appetitegourmet.api.models.Empresa;
import br.com.appetitegourmet.api.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
@PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
public class EmpresaController {
    private final EmpresaService empresaService;
    
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
    
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }
    
    @GetMapping("/{id}")
    public Empresa buscarEmpresaPorId(@PathVariable Long id) {
        return empresaService.buscarEmpresaPorId(id);
    }
    
    @PostMapping
    public Empresa salvarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.salvarEmpresa(empresa);
    }
    
    @DeleteMapping("/{id}")
    public void excluirEmpresa(@PathVariable Long id) {
        empresaService.excluirEmpresa(id);
    }

    @PutMapping("/{id}")
    public void editarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresaService.editarEmpresa(id, empresa);
    }
}
