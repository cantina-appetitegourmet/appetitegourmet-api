package br.com.appetitegourmet.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PessoaAlunoRequest {
    private String nomeCompleto;
    private Boolean sexo;
    private LocalDate nascimento;
    private String cpf;
}
