package br.com.appetitegourmet.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoPessoaEditReq {
    private Long id;
    private PessoaAlunoEditReq pessoa;
    private String restricaoAlimentar;
}
