package br.com.appetitegourmet.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Colunas;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="enderecos")
public class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length=Colunas.CEP)
    private String cep;
	
	@Column(nullable = false, length=Colunas.LOGRADOURO)
    private String logradouro;
	
	@Column(nullable = false, length=Colunas.BAIRRO)
    private String bairro;
	
	@Column(nullable = false, length=Colunas.CIDADE)
    private String cidade;
	
	@Column(nullable = false, length=Colunas.UF)
    private String uf;
	
	@Column(nullable = true)
    private int numero;
	
	@Column(nullable = true, length=Colunas.COMPLEMENTO)
    private String complemento;
}
