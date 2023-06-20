package br.com.appetitegourmet.api.models;

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
import utils.Turno_Enum;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ano_serie_unidade_turnos")
public class AnoSerieUnidadeTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Turno_Enum turno;
    
    @ManyToOne
    @JoinColumn(name = "ano_serie_unidade_id", nullable = false)
    private AnoSerieUnidade anoSerieUnidade;
}
