package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Contrato;
import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ContratoPlanoReq {
    private Contrato contrato;
    private PlanoAlimentarPreco planoAlimentarPreco;

    private Boolean segunda;
    private Boolean terca;
    private Boolean quarta;
    private Boolean quinta;
    private Boolean sexta;
    private Boolean sabado;
    private Boolean domingo;
}
