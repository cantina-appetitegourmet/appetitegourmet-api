package br.com.appetitegourmet.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(
            name = "responsavel_aluno",
            joinColumns = @JoinColumn(name = "responsavel_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    List<Aluno> alunos;

    @OneToMany(mappedBy="responsavel")
    private List<ResponsavelAluno> responsavelAlunos;
}
