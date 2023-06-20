package br.com.appetitegourmet.api.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="planos_alimentares_precos")
public class PlanoAlimentarPreco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "plano_alimentar_id", nullable = false)
    private PlanoAlimentar planoAlimentar;
    
    @ManyToOne
    @JoinColumn(name = "turma_anos_letivos_id", nullable = false)
    private TurmaAnoLetivo turmaAnoLetivo;
    
    @Column(name = "preco_dia", nullable = false)
    private BigDecimal precoDia;
    
    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}
