package br.com.appetitegourmet.api.models;

import java.sql.Date;

import lombok.Data;

@Data
public class Calendario {
    private AnoLetivo anoLetivo;
	
    private Cidade cidade;
	
	private Date data;
	
	private boolean util;
	
    private String observacao;
}
