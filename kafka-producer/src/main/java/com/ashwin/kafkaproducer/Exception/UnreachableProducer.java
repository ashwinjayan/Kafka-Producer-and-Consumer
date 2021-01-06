package com.ashwin.kafkaproducer.Exception;

public class UnreachableProducer extends RuntimeException {
    public UnreachableProducer(String message) {
        super(message);
    }

    public UnreachableProducer(String message, Throwable cause) {
        super(message, cause);
    }
}
