package com.site.gamingblog.exception;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;

public class FileUploadException extends SizeLimitExceededException {

    public FileUploadException(final String message, final long actual, final long permitted) {
        super(message, actual, permitted);
    }
}
