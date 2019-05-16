package com.gqk.protoss.service.exceptions;

public class AuthException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthException(Object Obj) {
        super(Obj.toString());
    }
}
