package com.awanrpn.invenmanager.controller;

import com.awanrpn.invenmanager.model.exception.UniversalModelExceptionBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEntryPointController implements AuthenticationEntryPoint {

    private final ApplicationContext context;
    private final UniversalModelExceptionBuilder universalModelExceptionBuilder;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("Spring Security Throw Exception when Auth");

        HandlerExceptionResolver her = context.getBean("handlerExceptionResolver", HandlerExceptionResolver.class);

        her.resolveException(request, response, null,
                universalModelExceptionBuilder.unauthoried(authException));
    }
}
