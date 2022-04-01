package com.task.init.controlleradvice;

import com.task.init.exceptions.*;
import com.task.init.models.dtos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class MovieControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SearchParameterEmptyException.class)
    public ResponseEntity<Object> handleSearchParameterEmptyException(SearchParameterEmptyException exception,
                                                                      HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Object> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException exception,
                                                                       HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryDoesNotExistException.class)
    public ResponseEntity<Object> handleCategoryDoesNotExistException(CategoryDoesNotExistException exception,
                                                                      HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MovieDoesNotExistException.class)
    public ResponseEntity<Object> handleMovieDoesNotExistException(MovieDoesNotExistException exception,
                                                                   HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FaultyRequestException.class)
    public ResponseEntity<Object> handleFaultyRequestException(FaultyRequestException exception,
                                                               HttpServletRequest request) {
        return new ResponseEntity<>(new ErrorDetails(new Date(), exception.getMessage(), request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

}
