package com.gabrieldalmoro.controlefinanceiro.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String recurso, Long id) {
        super(recurso + "Não encontrado(a) com este:" + id);
    }
}
