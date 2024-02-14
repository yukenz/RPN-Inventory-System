package com.awanrpn.invenmanager.controller.advice;

import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class UniversalExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public Object throwable(Throwable e) {
        return universalCustomExceptionTranslator(e);
//        return ResponsePayloadBuilder.err(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, 500);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public Object responseStatusException(ResponseStatusException e) {
        return ResponsePayloadBuilder.err(e.getReason(), e.getStatusCode(), e.getStatusCode().value());
    }

    private Object universalCustomExceptionTranslator(Throwable e) {

        /* HttpMessageNotReadableException */
        if (e instanceof HttpMessageNotReadableException) {
            return ResponsePayloadBuilder.err(e.getMessage(), HttpStatus.BAD_REQUEST, 400);
        }

        return ResponsePayloadBuilder.err(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR, 500);

    }
}
