package br.com.appetitegourmet.api.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import utils.Colunas;

@Entity
@Data
public class Pagamento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "contrato_id", nullable = false)
	private Contrato contrato;
	
	// 1 - PIX, 2 - BOLETO
	private Integer idMeioPagamento;
	
	// 1 - Gerencianet
	private Integer idEmpresaIntegracao;
	
	// 1 - GERADO, 2 - PAGO, 3 - CANCELADO
	private Integer idStatus;
	
	private BigDecimal valor;
	
	// Dados de localizacao da empresa de integracao
	@Column(nullable = true)
	private String dados;
	
}
