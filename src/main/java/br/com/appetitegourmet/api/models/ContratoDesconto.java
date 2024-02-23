package br.com.appetitegourmet.api.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ContratoDesconto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    @JsonIgnore
    private Contrato contrato;
    
    @ManyToOne
    @JoinColumn(name = "desconto_id", nullable = false)
    private Desconto desconto;
    
    @Column(name="data_inicio", nullable = true)
    private Date dataInicio;
    
    @Column(name="data_fim", nullable = true)
    private Date dataFim;
}
