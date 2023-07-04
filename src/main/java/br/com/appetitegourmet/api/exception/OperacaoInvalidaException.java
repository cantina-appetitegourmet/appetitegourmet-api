package br.com.appetitegourmet.api.exception;

public class OperacaoInvalidaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1631502483473108189L;

	public OperacaoInvalidaException(String message) {
        super(message);
    }
}
