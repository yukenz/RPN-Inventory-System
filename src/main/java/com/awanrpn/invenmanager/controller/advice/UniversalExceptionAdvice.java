package com.awanrpn.invenmanager.controller.advice;

import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class UniversalExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    Object throwable(Throwable e) {
        return ResponsePayloadBuilder.err(e, HttpStatus.INTERNAL_SERVER_ERROR, 500);
    }

    @ExceptionHandler(ResponseStatusException.class)
    Object responseStatusException(ResponseStatusException e) {
        return ResponsePayloadBuilder.err(new RuntimeException(e.getReason(), e), e.getStatusCode(), e.getStatusCode().value());
    }
}
