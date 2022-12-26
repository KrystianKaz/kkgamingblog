package com.site.gamingblog.exception;

public class CreatedUserExistException extends RuntimeException{

    public CreatedUserExistException(String message) {
        super(message);
    }
}
