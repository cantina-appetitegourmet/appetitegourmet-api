package br.com.appetitegourmet.api.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2883874771366898203L;

	public PessoaNaoEncontradaException(String message) {
        super(message);
    }
}
