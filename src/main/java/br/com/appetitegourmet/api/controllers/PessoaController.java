package br.com.appetitegourmet.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.dto.PessoaRequest;
import br.com.appetitegourmet.api.dto.PessoaResponse;
import br.com.appetitegourmet.api.exception.PessoaNaoEncontradaException;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.services.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@PreAuthorize("hasRole('OPERADOR') or hasRole('ADMIN')")
@AllArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    @PostMapping
    public ResponseEntity<PessoaResponse> criarPessoa(@RequestBody @Valid Pessoa pessoa)
            throws HttpMessageNotReadableException {
        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
        PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.pessoaToPessoaResponse(pessoaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponse);
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listaTodasPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();
        List<PessoaResponse> pessoaResponses = PessoaMapper.INSTANCE.pessoasToPessoaResponses(pessoas);
        return ResponseEntity.ok(pessoaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.buscarPessoaPorId(id);
        PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.pessoaToPessoaResponse(pessoa);
        return ResponseEntity.ok(pessoaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> editarPessoa(@PathVariable Long id,
            @RequestBody @Valid PessoaRequest pessoaRequest)
            throws PessoaNaoEncontradaException {
        Pessoa pessoaSalva = pessoaService.editarPessoa(id, pessoaRequest);
        PessoaResponse pessoaResponse = pessoaMapper.pessoaToPessoaResponse(pessoaSalva);
        return ResponseEntity.ok(pessoaResponse);
    }
}
