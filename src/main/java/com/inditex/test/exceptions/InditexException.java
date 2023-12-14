package com.inditex.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InditexException extends RuntimeException {
    private final ErrorCode code;

    public InditexException(ErrorCode code) {
        super(code.name());
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}