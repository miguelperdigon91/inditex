package com.inditex.test.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class InditexError {
    private Error error;

    @Data
    @Builder
    public static class Error {
        private String message;
        private HttpStatus status;
    }
}
