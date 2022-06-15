package com.example.model.exception;

public class UserServiceException extends Exception {
    public UserServiceException(){
        super();
    }

    public UserServiceException(String message){
        super(message);
    }

    public UserServiceException(Exception e){
        super(e);
    }

    public UserServiceException(String message, Exception e){
        super(message, e);
    }
}
