package com.GamingBlog.gamingblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserImageAdvice {

    @ResponseBody
    @ExceptionHandler(UserImageUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userImageHandler(UserImageUploadException exc) {
        return exc.getMessage();
    }
}
