package br.com.appetitegourmet.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.appetitegourmet.api.dto.PessoaResponse;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.services.PessoaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
        PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.pessoaToPessoaResponse(pessoaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponse);
    }
}
