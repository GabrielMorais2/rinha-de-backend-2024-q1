package com.moraes.gabriel.rinhadebackend2024q1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalExceptions(Exception ex, WebRequest request) {

        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof org.springframework.web.bind.MethodArgumentNotValidException ||
                ex instanceof org.springframework.web.HttpRequestMethodNotSupportedException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(ex.getMessage(), status);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErrorResponse> carAlreadyExistsException(SaldoInsuficienteException ex) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> clienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}