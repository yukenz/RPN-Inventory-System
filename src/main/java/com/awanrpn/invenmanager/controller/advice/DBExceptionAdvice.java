package com.awanrpn.invenmanager.controller.advice;

import com.awanrpn.invenmanager.model.response.ResponsePayloadBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class DBExceptionAdvice {

    @ExceptionHandler(ConnectException.class)
    ResponseEntity<?> connectException(ConnectException e) {
        return ResponsePayloadBuilder.err(e, HttpStatus.INTERNAL_SERVER_ERROR, 500);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<?> illegalArgumentException(IllegalArgumentException e) {
        return ResponsePayloadBuilder.err(e, HttpStatus.UNPROCESSABLE_ENTITY, 400);
    }


}
