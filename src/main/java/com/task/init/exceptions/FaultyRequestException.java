package com.task.init.exceptions;

public class FaultyRequestException extends RuntimeException {
    public FaultyRequestException(String message) {
        super(message);
    }
}
