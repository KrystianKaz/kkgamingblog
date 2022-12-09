package com.GamingBlog.gamingblog.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(String message) {
        super(message);
    }
}
