package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.services.ResponsavelAlunoService;

@RestController
@RequestMapping("/responsavelAlunos")
@PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
public class ResponsavelAlunoController {
	
	private final ResponsavelAlunoService responsavelAlunoService;
    
    public ResponsavelAlunoController(ResponsavelAlunoService responsavelAlunoService) {
        this.responsavelAlunoService = responsavelAlunoService;
    }
    
    @GetMapping
    public List<ResponsavelAluno> listarResponsavelAlunos() {
        return responsavelAlunoService.listarResponsavelAlunos();
    }
    
    @GetMapping("/{id}")
    public ResponsavelAluno buscarResponsavelAlunoPorId(@PathVariable Long id) {
        return responsavelAlunoService.buscarResponsavelAlunoPorId(id);
    }
    
    @PostMapping
    public ResponsavelAluno salvarResponsavelAluno(@RequestBody ResponsavelAluno responsavelAluno) {
        return responsavelAlunoService.salvarResponsavelAluno(responsavelAluno);
    }
    
    @DeleteMapping("/{id}")
    public void excluirResponsavelAluno(@PathVariable Long id) {
        responsavelAlunoService.excluirResponsavelAluno(id);
    }

}
