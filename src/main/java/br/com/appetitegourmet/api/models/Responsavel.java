package br.com.appetitegourmet.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    @JsonManagedReference
    private Pessoa pessoa;

    @OneToMany(mappedBy="responsavel", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ResponsavelAluno> responsavelAlunos;
}
