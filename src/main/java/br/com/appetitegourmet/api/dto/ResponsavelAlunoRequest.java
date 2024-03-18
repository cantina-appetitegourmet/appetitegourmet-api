package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Parentesco;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsavelAlunoRequest {
    AlunoPessoaRequest aluno;
    Parentesco parentesco;
}
