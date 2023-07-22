package br.com.appetitegourmet.api.exception;

public class ErroParametroObrigatorioException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041178691026825826L;
	
	public ErroParametroObrigatorioException(String message) {
		super(message);
	}

}
