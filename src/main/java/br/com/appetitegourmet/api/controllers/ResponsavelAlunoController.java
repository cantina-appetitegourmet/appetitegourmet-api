package br.com.appetitegourmet.api.controllers;

import java.util.List;
import java.util.Optional;

import br.com.appetitegourmet.api.dto.ResponsavelAlunoEditReq;
import br.com.appetitegourmet.api.dto.ResponsavelAlunoRequest;
import br.com.appetitegourmet.api.dto.ResponsavelAlunoResponse;
import br.com.appetitegourmet.api.mapper.ResponsavelAlunoMapper;
import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.spring.login.models.User;
import br.com.appetitegourmet.api.spring.login.repository.UserRepository;
import br.com.appetitegourmet.api.spring.login.security.jwt.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.appetitegourmet.api.models.ResponsavelAluno;
import br.com.appetitegourmet.api.services.ResponsavelAlunoService;

@RestController
@RequestMapping("/responsavelAlunos")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200,https://nice-beach-01dafa610.3.azurestaticapps.net,https://menukids.appetitegourmet.com.br", maxAge = 3600, allowCredentials="true")
public class ResponsavelAlunoController {
    private final JwtUtils jwtUtils;
    UserRepository userRepository;
	private final ResponsavelAlunoService responsavelAlunoService;
    private final ResponsavelAlunoMapper responsavelAlunoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
    public List<ResponsavelAlunoResponse> responsavelAlunos(HttpServletRequest request) {
        String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.getJwtFromCookies(request));
        Optional<User> user = userRepository.findByUsername(username);
        List<ResponsavelAluno> responsavelAlunos = user.get().getPessoa().getResponsavel().getResponsavelAlunos();
        return responsavelAlunoMapper.INSTANCE.responsavelAlunosToResponsavelAlunosResponse(responsavelAlunos);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public List<ResponsavelAluno> listarResponsavelAlunos() {
        return responsavelAlunoService.listarResponsavelAlunos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_RESPONSAVEL')")
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

    @PutMapping
    @PreAuthorize("hasRole('ROLE_RESPONSAVEL')")
    public ResponsavelAluno editarResponsavelAluno(HttpServletRequest request, @RequestBody ResponsavelAlunoEditReq responsavelAlunoEditReq) {
        return responsavelAlunoService.editarResponsavelAluno(request, responsavelAlunoEditReq);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OPERADOR') or hasRole('ROLE_ADMIN')")
    public void excluirResponsavelAluno(@PathVariable Long id) {
        responsavelAlunoService.excluirResponsavelAluno(id);
    }

}
