package com.example.model.exception;

public class ServiceWrongPasswordException extends ServiceException{
    public ServiceWrongPasswordException(){
        super();
    }

    public ServiceWrongPasswordException(String message){
        super(message);
    }

    public ServiceWrongPasswordException(Exception e){
        super(e);
    }

    public ServiceWrongPasswordException(String message, Exception e){
        super(message, e);
    }
}
