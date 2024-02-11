package com.awanrpn.invenmanager.model.exception;

import com.awanrpn.invenmanager.config.ErrorMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UniversalModelExceptionBuilder {

    private final MessageSource errorMessageSource;

    public ResponseStatusException actionFailed(String action, Throwable ex) {
        return new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorMessageSource.getMessage(
                        "universal.action_failed",
                        new Object[]{action, ex},
                        ErrorMessageSource.locale()
                )
        );
    }

    public ResponseStatusException unauthoried(Throwable ex) {
        return new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                errorMessageSource.getMessage(
                        "universal.unauthorized",
                        new Object[]{ex.getMessage()},
                        ErrorMessageSource.locale()
                )
        );
    }
}
