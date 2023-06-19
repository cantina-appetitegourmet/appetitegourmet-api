package br.com.appetitegourmet.api.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PessoaDTO {
    private int id;
    private String nomeCompleto;
    private boolean sexo;
    private Date nascimento;
    private String cpf;
    private String telefone;
    private String email;

    public String getSexoTextual() {
        return sexo ? "Masculino" : "Feminino";
    }
}
