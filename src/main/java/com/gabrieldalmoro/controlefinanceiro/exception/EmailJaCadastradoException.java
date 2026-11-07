package com.gabrieldalmoro.controlefinanceiro.exception;

public class EmailJaCadastradoException extends RuntimeException{
    public EmailJaCadastradoException(String email) {
        super("E-mail já cadastrado: " + email);
    }
}
