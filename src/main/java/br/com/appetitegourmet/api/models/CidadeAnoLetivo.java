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

@Entity
@Data
@Table(name="cidades_anos_letivos")
public class CidadeAnoLetivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
    
    @ManyToOne
    @JoinColumn(name = "ano_letivo_id", nullable = false)
    private AnoLetivo anoLetivo;
    
    @Column(name="inicio_ano_letivo", nullable = false)
    private Date inicioAnoLetivo;
    
    @Column(name="fim_primeiro_semestre", nullable = false)
    private Date fimPrimeiroSemestre;
    
    @Column(name="inicio_segundo_semestre", nullable = false)
    private Date inicioSegundSemestre;
    
    @Column(name="fim_ano_letivo", nullable = false)
    private Date fimAnoLetivo;
}
