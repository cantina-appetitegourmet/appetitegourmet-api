package br.com.appetitegourmet.api.exception;

public class EntidadeObrigatoriaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1212209411011324295L;

	public EntidadeObrigatoriaException(String message) {
        super(message);
    }
}
