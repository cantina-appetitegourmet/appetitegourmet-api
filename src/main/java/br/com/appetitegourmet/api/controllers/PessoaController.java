package br.com.appetitegourmet.api.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.appetitegourmet.api.dto.PessoaResponse;
import br.com.appetitegourmet.api.exception.dto.ErrorResponse;
import br.com.appetitegourmet.api.mapper.PessoaMapper;
import br.com.appetitegourmet.api.models.Pessoa;
import br.com.appetitegourmet.api.services.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {
    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> criarPessoa(@RequestBody @Valid Pessoa pessoa)
            throws HttpMessageNotReadableException {
        Pessoa pessoaSalva = pessoaService.salvarPessoa(pessoa);
        PessoaResponse pessoaResponse = PessoaMapper.INSTANCE.pessoaToPessoaResponse(pessoaSalva);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlePessoaInvalidaException(HttpMessageNotReadableException ex) {
        String errorMessage = "Erro de desserialização: o valor do campo booleano é inválido.";
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorMessage,
                ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
