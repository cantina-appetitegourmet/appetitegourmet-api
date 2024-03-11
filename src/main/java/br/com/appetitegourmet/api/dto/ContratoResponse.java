package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ContratoResponse {
    private Long id;
    private Unidade unidade;
    private TurmaAnoLetivo turmaAnoLetivo;
    private Date dataAdesao;
    private List<ContratoDesconto> contratoDescontos;
    private List<ContratoPlano> contratoPlanos;
    private List<Pagamento> pagamentos;
}
