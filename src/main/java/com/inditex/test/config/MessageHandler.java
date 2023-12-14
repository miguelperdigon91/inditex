package com.inditex.test.config;

import com.inditex.test.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageHandler {
    private final MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getMessage(ErrorCode code) {
        return this.getMessage(code.messageCode());
    }
}
