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
@Table(name="colegios")
public class Colegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=Colunas.NOME)
    private String nome;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
