package com.task.init.exceptions;

public class CategoryDoesNotExistException extends RuntimeException {
    public CategoryDoesNotExistException(String message) {
        super(message);
    }
}
