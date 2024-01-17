package com.estudos.crud.service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensagem){
       super(mensagem);
    }
}
