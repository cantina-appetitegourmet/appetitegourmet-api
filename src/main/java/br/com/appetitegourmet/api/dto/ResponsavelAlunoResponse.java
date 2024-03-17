package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Aluno;
import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.Parentesco;
import br.com.appetitegourmet.api.models.Responsavel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsavelAlunoResponse {
    private Long id;
    private Aluno aluno;
    private Responsavel responsavel;
    private Parentesco parentesco;
    private List<Contrato> contratos;
    private Boolean ativo = true;
}
