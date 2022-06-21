package com.example.model.service.exception;

public class UserServiceException extends ServiceException {
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
