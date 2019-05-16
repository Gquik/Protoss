package com.gqk.protoss.service.exceptions;

public class OrderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderException(Object Obj) {
        super(Obj.toString());
    }
}
