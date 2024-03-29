package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import br.com.appetitegourmet.api.models.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResponse {
    private Long id;
    private String nomeCompleto;
    private Boolean sexo;
    private String sexoExtenso;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String emailSecundario;
    private Endereco endereco;
}
