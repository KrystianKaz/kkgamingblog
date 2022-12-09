package com.GamingBlog.gamingblog.exception;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(SizeLimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String fileUploadSizeExceededHandler(SizeLimitExceededException exc) {
        return ("File size exceeded!");
    }
}
