package br.com.appetitegourmet.api.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "responsavel_aluno_id", nullable = false)
    private ResponsavelAluno responsavelAluno;
    
    @ManyToOne
    @JoinColumn(name = "turma_anos_letivos_id", nullable = false)
    private TurmaAnoLetivo turmaAnoLetivo;
    
    @Column(name="data_adesao", nullable = false)
    private Date dataAdesao;
}
