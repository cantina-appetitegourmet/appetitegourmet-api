package utils.pagamentos.gerencianet;

import lombok.Data;

@Data
public class DescontoCondicional {

	private String tipo;
	private long valor;
	private String dataMaxima;
}
