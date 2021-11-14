package com.alexpoletaev.order.exception;

public class RequestProcessingException extends RuntimeException {

    private static final long serialVersionUID = 2567596579116128427L;

    public RequestProcessingException(String message) {
        super(message);
    }
}
