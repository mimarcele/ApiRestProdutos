package com.produtos.apirest.handler;

import com.produtos.apirest.exceptions.ProdutoNotFoundException;
import com.produtos.apirest.exceptions.ResourceNotFoundDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ProdutoNotFoundException produtoNotFoundException){
    ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.ResourceNotFoundDetailsBuilder.newBuilder()
            .timestamp(new Date().getTime())
            .status(HttpStatus.NOT_FOUND.value())
            .title("Produto não encontrado")
            .details(produtoNotFoundException.getMessage())
            .developerMessage(produtoNotFoundException.getClass().getName())
            .build();

    return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }
}

