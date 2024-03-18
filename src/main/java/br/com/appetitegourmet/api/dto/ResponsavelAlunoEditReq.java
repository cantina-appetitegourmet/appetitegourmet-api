package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Parentesco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelAlunoEditReq {
    private Long id;
    private AlunoPessoaEditReq aluno;
    private Parentesco parentesco;
}
