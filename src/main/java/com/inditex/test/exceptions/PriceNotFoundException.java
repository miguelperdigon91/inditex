package com.inditex.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.inditex.test.exceptions.ErrorCode.PRICE_NOT_FOUND;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends InditexException {
    public PriceNotFoundException() {
        super(PRICE_NOT_FOUND);
    }
}