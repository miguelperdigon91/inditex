package com.inditex.test.config;

import com.inditex.test.exceptions.InditexException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InditexExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageHandler messageHandler;

    public InditexExceptionHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @ExceptionHandler({InditexException.class})
    public ResponseEntity<Object> handleInventoryEx(final InditexException ex, final WebRequest request) {
        logger.error("Handling exception", ex);

        final InditexError oneError = InditexError.builder()
                .error(buildError(ex))
                .build();
        return new ResponseEntity<Object>(oneError, new HttpHeaders(), oneError.getError().getStatus());
    }

    private InditexError.Error buildError(InditexException ex) {
        ResponseStatus status = ex.getClass().getAnnotation(ResponseStatus.class);

        return InditexError.Error.builder()
                .message(messageHandler.getMessage(ex.getCode()))
                .status(status.value())
                .build();
    }
}
