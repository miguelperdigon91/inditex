package com.inditex.test.exceptions;

public enum ErrorCode {
    PRICE_NOT_FOUND;

    private static final String EXCEPTION = "exception.";

    public String messageCode() {
        return EXCEPTION + this.name();
    }

}