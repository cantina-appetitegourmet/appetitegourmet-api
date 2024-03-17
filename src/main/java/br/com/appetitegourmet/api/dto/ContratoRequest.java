package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.TurmaAnoLetivo;
import br.com.appetitegourmet.api.models.Unidade;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContratoRequest {
    List<ContratoPlanoRequest> contratoPlanos;
    ResponsavelAlunoId responsavelAluno;
    TurmaAnoLetivo turmaAnoLetivo;
    Unidade unidade;
}
