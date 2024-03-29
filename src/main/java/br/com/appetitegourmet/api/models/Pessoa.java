package br.com.appetitegourmet.api.models;

import java.time.LocalDate;

import br.com.appetitegourmet.api.spring.login.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import utils.ValidacaoConstantes;

@Entity
@Getter
@Setter
public class Pessoa {
    
    public static final boolean SEXO_MASCULINO = true;
    public static final boolean SEXO_FEMININO = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{campo.nomeCompleto.obrigatorio}")
    @Size(max = ValidacaoConstantes.TAMANHO_MAXIMO_NOME_COMPLETO)
    private String nomeCompleto;

    private boolean sexo;

    private LocalDate nascimento;

    @CPF
    private String cpf;

    @Size(max = ValidacaoConstantes.TAMANHO_MAXIMO_TELEFONE)
    private String telefone;

    @Email
    @Size(max = ValidacaoConstantes.TAMANHO_MAXIMO_EMAIL)
    private String email;
    
    @Email
    @Size(max = ValidacaoConstantes.TAMANHO_MAXIMO_EMAIL)
    private String emailSecundario;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(mappedBy = "pessoa")
    @JsonBackReference
    private User user;

    @OneToOne(mappedBy = "pessoa")
    @JsonBackReference
    private Responsavel responsavel;

    @OneToOne(mappedBy = "pessoa")
    @JsonBackReference
    private Aluno aluno;
}
