package br.com.appetitegourmet.api.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Desconto {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=Colunas.MOTIVO)
    private String motivo;
    
    @Column(nullable = false)
    private BigDecimal valor_percentual;
    
    @Builder.Default
    @Column(nullable = false)
    private Integer tipo = 0;
}
