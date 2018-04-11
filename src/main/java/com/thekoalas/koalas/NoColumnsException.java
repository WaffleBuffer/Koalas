package com.thekoalas.koalas;

public class NoColumnsException extends RuntimeException{

    public NoColumnsException() {
    }

    public NoColumnsException(String message) {
        super(message);
    }

    public NoColumnsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoColumnsException(Throwable cause) {
        super(cause);
    }

    public NoColumnsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
