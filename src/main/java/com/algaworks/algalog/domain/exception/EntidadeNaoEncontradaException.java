package com.algaworks.algalog.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

    private static final Long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
