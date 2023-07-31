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
import utils.Colunas;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cantinas_empresa")
public class Empresa {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, length=Colunas.CNPJ)
    private String cnpj;
	
	@Column(nullable = true, length=Colunas.INSCRICAO_ESTADUAL)
    private String inscricaoEstadual;
	
	@Column(nullable = true, length=Colunas.INSCRICAO_MUNICIPAL)
    private String inscricaoMunicipal;
	
	@Column(nullable = false, length=Colunas.NOME)
    private String razaoSocial;
	
	@Column(nullable = false, length=Colunas.NOME)
    private String nomeFantasia;
	
	@Column(name = "id_empresa_integracao_pix", nullable = true)
    private Integer idEmpresaIntegracaoPix;
	
	@Column(nullable = true)
    private String dadosIntegracaoPix;
	
	@Column(nullable = true)
    private String chavePix;
	
	@Column(name = "id_empresa_integracao_boleto", nullable = true)
    private Integer idEmpresaIntegracaoBoleto;
	
	@Column(nullable = true)
    private String dadosIntegracaoBoleto;
	
	@OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;
	
	@Builder.Default
	@Column(nullable = false)
    private boolean ativo = true;
}
