package br.com.appetitegourmet.api.models;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "unidade_id", nullable = false)
    private Unidade unidade;
    
    @ManyToOne
    @JoinColumn(name = "responsavel_aluno_id", nullable = false)
    @JsonIgnore
    private ResponsavelAluno responsavelAluno;
    
    @ManyToOne
    @JoinColumn(name = "turma_anos_letivos_id", nullable = false)
    private TurmaAnoLetivo turmaAnoLetivo;
    
    @Column(name="data_adesao", nullable = false)
    private Date dataAdesao;

    @OneToMany(mappedBy="contrato")
    @JsonManagedReference
    private List<ContratoDesconto> contratoDescontos;

    @OneToMany(mappedBy="contrato")
    @JsonManagedReference
    private List<ContratoPlano> contratoPlanos;

    @OneToMany(mappedBy="contrato")
    @JsonManagedReference
    private List<Pagamento> pagamentos;
}
