package br.com.appetitegourmet.api.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class BoletoGerarBoletoRequest {

	private long idResonsavel;
	private Long idPagamento;
	private long idUnidade;
    private Date dataExpiracao;
    private Date dataMaximaDescontoCondicionalBoleto;
    private String mensagem;
    private String descricao;
    private BigDecimal valor;
}
