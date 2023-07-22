package utils.pagamentos.gerencianet;

import lombok.Data;

@Data
public class Cliente {
	
	public static final int PESSOA_FISICA = 1;
	public static final int PESSOA_JURIDICA = 2;
	
	private int tipoPessoa;
	// PESSOA FISICA
	private String nome;
	private String cpf;
	// PESSOA JURIDICA
	private String razaoSocial;
	private String cnpj;
	// DEMAIS DADOS
	private String email;
	private String telefone;
	private String dataNascimento;
	// Endereco
	private String cep;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
}
