package br.com.appetitegourmet.api.dto;

import java.time.LocalDate;

import br.com.appetitegourmet.api.models.Endereco;
import br.com.appetitegourmet.api.models.Pessoa;
import lombok.Data;

@Data
public class CadastroResponsavelComUsuarioRequest {
	private String senha;
	private Pessoa pessoa;
}
