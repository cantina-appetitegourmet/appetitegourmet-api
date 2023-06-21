package br.com.appetitegourmet.api.exception;

public class PessoaInvalidaException extends RuntimeException {
    public PessoaInvalidaException(String message) {
        super(message);
    }
}
