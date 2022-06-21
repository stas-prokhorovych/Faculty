package com.example.model.service.exception;

public class JournalServiceException extends ServiceException {
    public JournalServiceException(){
        super();
    }

    public JournalServiceException(String message){
        super(message);
    }

    public JournalServiceException(Exception e){
        super(e);
    }

    public JournalServiceException(String message, Exception e){
        super(message, e);
    }
}
