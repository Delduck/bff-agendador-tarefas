package com.delduck.bffagendadortarefas.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
