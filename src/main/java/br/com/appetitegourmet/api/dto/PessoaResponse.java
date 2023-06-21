package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PessoaResponse {
    private Long id;
    private String nomeCompleto;
    private Boolean sexo;
    private String sexoExtenso;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;
}
