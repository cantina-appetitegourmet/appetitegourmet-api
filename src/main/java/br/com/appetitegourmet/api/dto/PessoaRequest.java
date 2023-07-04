package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import br.com.appetitegourmet.api.models.Endereco;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class PessoaRequest {
    private String nomeCompleto;
    private Boolean sexo;
    private LocalDate nascimento;
    private String cpf;
    private String telefone;
    private String email;
    private Endereco endereco;
}
