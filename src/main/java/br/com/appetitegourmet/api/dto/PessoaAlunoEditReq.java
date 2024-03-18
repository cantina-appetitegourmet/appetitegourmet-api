package br.com.appetitegourmet.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PessoaAlunoEditReq {
    private Long id;
    private String nomeCompleto;
    private Boolean sexo;
    private LocalDate nascimento;
    private String cpf;
}
