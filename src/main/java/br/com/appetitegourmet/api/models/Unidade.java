package br.com.appetitegourmet.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="unidades")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome", nullable = false, length=Colunas.NOME)
    private String nome_unidade_escolar;
    
    @Column(name = "cantinaNome", nullable = false, length=Colunas.NOME)
    private String nome_unidade_negocio;
    
    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = true)
    private Endereco endereco;
    
    @ManyToOne
    @JoinColumn(name = "colegio_id", nullable = false)
    private Colegio colegio;
    
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
