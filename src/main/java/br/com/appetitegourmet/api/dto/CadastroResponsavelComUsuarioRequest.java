package br.com.appetitegourmet.api.dto;

import br.com.appetitegourmet.api.models.Pessoa;
import lombok.Data;

@Data
public class CadastroResponsavelComUsuarioRequest {
	private String senha;
	private Pessoa pessoa;
}
