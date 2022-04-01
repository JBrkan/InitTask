package com.task.init.exceptions;

public class MovieDoesNotExistException extends RuntimeException {
    public MovieDoesNotExistException(String message) {
        super(message);
    }
}
