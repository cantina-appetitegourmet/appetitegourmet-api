package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Parentesco;
import lombok.Data;

@Data
public class ResponsavelAlunoRequest {
    AlunoPessoaRequest aluno;
    Parentesco parentesco;
}
