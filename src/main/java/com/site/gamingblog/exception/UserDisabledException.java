package com.site.gamingblog.exception;

import org.springframework.security.core.AuthenticationException;

public class UserDisabledException extends AuthenticationException {

    public UserDisabledException(String message) {
        super(message);
    }
}
