package br.com.appetitegourmet.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name="reponsaveis")
public class Responsavel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length=100)
    private String nome;
	
	@Column(nullable = false, unique=true, length=11)
    private String cpf;
	
	@OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;
    
    @Column(nullable = false, length=300)
    private String email;
    
    @Column(nullable = true, length=11)
    private String telefone;
}
