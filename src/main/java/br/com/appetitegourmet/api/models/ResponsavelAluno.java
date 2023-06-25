package br.com.appetitegourmet.api.models;

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
public class ResponsavelAluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
	private Responsavel responsavel;
	
	@ManyToOne
    @JoinColumn(name = "grau_parentesco_id", nullable = false)
	private Parentesco parentesco;
	
	@Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
