package br.com.appetitegourmet.api.exception;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String message) {
        super(message);
    }
}
