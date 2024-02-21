package br.com.appetitegourmet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
	@JsonIgnore
	private Responsavel responsavel;
	
	@ManyToOne
    @JoinColumn(name = "grau_parentesco_id", nullable = false)
	private Parentesco parentesco;

	@OneToMany(mappedBy="responsavelAluno")
	@JsonIgnore
	private List<Contrato> contratos;
	
	@Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
