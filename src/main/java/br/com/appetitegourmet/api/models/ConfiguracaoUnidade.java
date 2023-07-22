package br.com.appetitegourmet.api.models;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="configuracoes_unidades")
public class ConfiguracaoUnidade {

	@Id
	@OneToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;
    
	@Column(nullable = false)
	private Integer tipoDescontoBoleto;
	
	@Column(nullable = false)
    private BigDecimal valorDescontoBoleto;
	
	@Column(nullable = false)
	private Integer tipoDescontoCondicionalBoleto;
	
	@Column(nullable = false)
    private BigDecimal valorDescontoCondicionalBoleto;
	
	@Column(nullable = false)
    private BigDecimal valorMultaBoleto;
	
	@Column(nullable = false)
    private BigDecimal valorJurosDiaBoleto;
}
