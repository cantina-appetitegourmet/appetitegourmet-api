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
@Table(name="feriados")
public class Feriado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "cidade_id", nullable = true)
    private Cidade cidade;
	
	@ManyToOne
    @JoinColumn(name = "estado_id", nullable = true)
    private Estado estado;
	
	@Column(nullable = false)
	private Date data;
	
	@Column(nullable = false, length=Colunas.DESCRITIVO)
    private String observacao;
}
