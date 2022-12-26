package com.site.gamingblog.exception;

public class RequestNotFoundException extends IllegalArgumentException{

    public RequestNotFoundException(long id) {
        super("Could not find request with id: " + id);
    }

    public RequestNotFoundException(String s) {
        super("Could not find request named: " + s);
    }
}
