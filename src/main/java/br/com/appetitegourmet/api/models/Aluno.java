package br.com.appetitegourmet.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import utils.Colunas;

import java.util.List;

@Entity
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    
    @Column(name="restricao_alimentar", nullable = true, length=Colunas.RESTRICAO_ALIMENTAR)
    private String RestricaoAlimentar;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnore
    List<Responsavel> responsavels;
}
