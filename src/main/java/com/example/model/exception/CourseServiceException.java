package com.example.model.exception;

public class CourseServiceException extends Exception {
    public CourseServiceException(){
        super();
    }

    public CourseServiceException(String message){
        super(message);
    }

    public CourseServiceException(Exception e){
        super(e);
    }

    public CourseServiceException(String message, Exception e){
        super(message, e);
    }
}
