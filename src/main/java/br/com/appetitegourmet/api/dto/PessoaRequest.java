package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import br.com.appetitegourmet.api.models.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaRequest {
    private String nomeCompleto;
    private Boolean sexo;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String emailSecundario;
    private Endereco endereco;
}
