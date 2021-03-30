package com.inditex.pricesws.exceptions;

public class NoDataFoundException extends RuntimeException {
    static final long serialVersionUID = -6715696386656257539L;

    public NoDataFoundException(String msg) {
        super(msg);
    }
}
