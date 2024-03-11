package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.PlanoAlimentarPreco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoPlanoRequest {
    private PlanoAlimentarPreco planoAlimentarPreco;

    private Boolean segunda;
    private Boolean terca;
    private Boolean quarta;
    private Boolean quinta;
    private Boolean sexta;
    private Boolean sabado;
    private Boolean domingo;
}
