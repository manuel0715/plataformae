package com.plataformae.ws.util.exeptions;

public class Exceptions extends RuntimeException {

    public Exceptions(String message) {
        super(message);
    }

    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }
}