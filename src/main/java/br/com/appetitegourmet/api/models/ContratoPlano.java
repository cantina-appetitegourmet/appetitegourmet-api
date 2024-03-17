package br.com.appetitegourmet.api.models;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.appetitegourmet.api.dto.ContratoPlanoReq;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoPlano {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    @JsonManagedReference
    private Contrato contrato;
    
    @ManyToOne
    @JoinColumn(name = "planos_alimentares_precos_id", nullable = false)
    private PlanoAlimentarPreco planoAlimentarPreco;
    
    private Boolean segunda;
    private Boolean terca;
    private Boolean quarta;
    private Boolean quinta;
    private Boolean sexta;
    private Boolean sabado;
    private Boolean domingo;

    @Column(name = "preco_dia", nullable = true)
    private BigDecimal precoDia;
    
    @Column(name="data_inicio", nullable = true)
    private Date dataInicio;
    
    @Column(name="data_fim", nullable = true)
    private Date dataFim;
}
