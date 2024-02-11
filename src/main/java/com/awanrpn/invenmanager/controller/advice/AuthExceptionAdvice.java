package com.awanrpn.invenmanager.controller.advice;

import com.awanrpn.invenmanager.model.dto.ResponsePayloadBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class AuthExceptionAdvice {

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<?> connectException(AuthenticationException e) {
        return ResponsePayloadBuilder.err(e, HttpStatus.FORBIDDEN, 400);
    }
}
