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
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=60)
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
    
    @ManyToOne
    @JoinColumn(name = "colegio_id", nullable = false)
    private Colegio colegio;
}
