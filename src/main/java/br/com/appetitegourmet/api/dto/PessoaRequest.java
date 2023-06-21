package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PessoaRequest {
    private String nomeCompleto;
    private Boolean sexo;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;
}
