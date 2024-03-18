package br.com.appetitegourmet.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.Colunas;

import java.util.List;

@Entity
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    @JsonManagedReference
    private Pessoa pessoa;
    
    @Column(name="restricao_alimentar", nullable = true, length=Colunas.RESTRICAO_ALIMENTAR)
    private String RestricaoAlimentar;

    @OneToMany(mappedBy="aluno")
    @JsonBackReference
    private List<ResponsavelAluno> responsavelAlunos;
}
