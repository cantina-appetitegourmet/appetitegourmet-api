package br.com.appetitegourmet.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsavelAluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "aluno_id", nullable = false)
	@JsonManagedReference
	private Aluno aluno;
	
	@ManyToOne
    @JoinColumn(name = "responsavel_id", nullable = false)
	@JsonManagedReference
	private Responsavel responsavel;
	
	@ManyToOne
    @JoinColumn(name = "grau_parentesco_id", nullable = false)
	private Parentesco parentesco;

	@OneToMany(mappedBy="responsavelAluno", fetch = FetchType.EAGER)
	@JsonBackReference
	private List<Contrato> contratos;
	
	@Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
