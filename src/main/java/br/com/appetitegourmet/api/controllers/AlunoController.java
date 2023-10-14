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

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.services.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private final AlunoService alunoService;
    
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    
    @GetMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public List<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public Aluno buscarAlunoPorId(@PathVariable Long id) {
        return alunoService.buscarAlunoPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN') or hasRole('RESPONSAVEL')")
    public Aluno salvarAluno(@RequestBody Aluno aluno) {
        return alunoService.salvarAluno(aluno);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void excluirAluno(@PathVariable Long id) {
        alunoService.excluirAluno(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
    public void editarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        alunoService.editarAluno(id, aluno);
    }
}
