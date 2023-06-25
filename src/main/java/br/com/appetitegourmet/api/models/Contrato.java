package br.com.appetitegourmet.api.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Contrato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "responsavel_aluno_id", nullable = false)
    private ResponsavelAluno responsavelAluno;
    
    @ManyToOne
    @JoinColumn(name = "turma_anos_letivos_id", nullable = false)
    private TurmaAnoLetivo turmaAnoLetivo;
    
    @Column(name="data_adesao", nullable = true)
    private Date dataAdesao;
}
