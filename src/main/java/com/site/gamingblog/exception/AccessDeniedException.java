package com.site.gamingblog.exception;

public class AccessDeniedException extends org.springframework.security.access.AccessDeniedException {

    public AccessDeniedException(final String msg) {
        super(msg);
    }
}
