package br.com.appetitegourmet.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="aluno_turma_anos_letivos")
public class AlunoTurmaAnosLetivos {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;
	
	@ManyToOne
    @JoinColumn(name = "turma_ano_letivo_id", nullable = false)
	private TurmaAnoLetivo turmaAnoLetivo;

}
