package br.com.appetitegourmet.api.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class BoletoGerarBoletoRequest {

	private long idResonsavel; // 1
	private Long idPagamento;  // 10
	private long idUnidade;    // 1
    private Date dataExpiracao; // 2023-07-30
    private Date dataMaximaDescontoCondicionalBoleto; // 2023-7-27
    private String mensagem; // Descontos para pagamentos até 27/07/2023 
    private String descricao; // Mensalidade Cantina
    private BigDecimal valor; // 120.00
}
