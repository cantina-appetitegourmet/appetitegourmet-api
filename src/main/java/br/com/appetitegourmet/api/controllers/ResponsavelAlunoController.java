package br.com.appetitegourmet.api.controllers;

import java.util.List;

import br.com.appetitegourmet.api.dto.ResponsavelAlunoRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ResponsavelAlunoController {
	
	private final ResponsavelAlunoService responsavelAlunoService;
    
    @GetMapping
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<ResponsavelAluno> listarResponsavelAlunos() {
        return responsavelAlunoService.listarResponsavelAlunos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public ResponsavelAluno buscarResponsavelAlunoPorId(@PathVariable Long id) {
        return responsavelAlunoService.buscarResponsavelAlunoPorId(id);
    }

    @GetMapping("/responsavelId/{responsavelId}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
    public List<ResponsavelAluno> buscarResponsavelAlunoPorIdResponsavel(@PathVariable Long responsavelId) {
        return responsavelAlunoService.buscarAlunosPorResponsavel(responsavelId);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
    public ResponsavelAluno salvarResponsavelAluno(HttpServletRequest request, @RequestBody ResponsavelAlunoRequest responsavelAlunoRequest) {
        return responsavelAlunoService.salvarResponsavelAluno(request, responsavelAlunoRequest);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirResponsavelAluno(@PathVariable Long id) {
        responsavelAlunoService.excluirResponsavelAluno(id);
    }

}
