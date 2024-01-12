package br.com.appetitegourmet.api.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import utils.Colunas;

@Entity
@Data
@Table(name="calendarios")
public class Calendario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "ano_letivo_id", nullable = false)
    private AnoLetivo anoLetivo;
	
	@ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
	
	@Column(nullable = false)
	private Date data;
	
	@Column(nullable = false)
	private boolean util;
	
	@Column(nullable = false, length=Colunas.DESCRITIVO)
    private String observacao;
}
