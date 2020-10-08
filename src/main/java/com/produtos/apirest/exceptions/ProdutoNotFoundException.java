package com.produtos.apirest.exceptions;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(String message){
    super(message);
    }
}
