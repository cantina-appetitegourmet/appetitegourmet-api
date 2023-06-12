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
@Table(name="tipos_contatos")
public class TipoContato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=Colunas.DESCRICAO_TIPO_CONTATO)
    private String descricao;
}
