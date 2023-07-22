package br.com.appetitegourmet.api.dto;

import lombok.Data;

@Data
public class PixCobrancaImediataSemTxidRequest {
	
	private String cpf; 
	private String nome; 
	private String valor; 
	private String chave;
	private String solicitacao;
}
