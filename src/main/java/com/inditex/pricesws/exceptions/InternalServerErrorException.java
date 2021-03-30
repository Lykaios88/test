package com.inditex.pricesws.exceptions;

public class InternalServerErrorException extends RuntimeException{
    static final long serialVersionUID = 4773494659224681524L;

    public InternalServerErrorException(String msg) {
        super(msg);
    }
}
