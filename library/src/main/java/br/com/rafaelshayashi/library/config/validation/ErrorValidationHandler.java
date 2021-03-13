package br.com.rafaelshayashi.library.config.validation;

import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ErrorValidationHandler {

    private final MessageSource messageSource;

    @Autowired
    public ErrorValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RetryableException.class)
    public ErrorMessageResponse handleRetryableException(RetryableException exception){
        return new ErrorMessageResponse("Erro connection catalogue service", Collections.emptyList());
    }
}
