package br.com.appetitegourmet.api.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dependente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length=100)
    private String nome;
	
	@Column(nullable = true, length=8)
    private String cep;
    
    @Column(nullable = true, length=100)
    private String logradouro;
    
    @Column(nullable = true, length=100)
    private String bairro;
    
    @Column(nullable = true, length=100)
    private String cidade;
    
    @Column(nullable = true, length=2)
    private String uf;
    
    @Column(nullable = true)
    private int numero;
    
    @Column(nullable = true, length=100)
    private String complemento;
    
    @Column(nullable = true)
    private Date dataNascimento;
}
