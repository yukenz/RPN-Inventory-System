package com.awanrpn.invenmanager.model.exception;

import com.awanrpn.invenmanager.config.ErrorMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UserModelExceptionBuilder {

    private final MessageSource errorMessageSource;

    /* Read Failed */
    public ResponseStatusException userNotFound(String uuid) {

        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                errorMessageSource.getMessage(
                        "userMessage.not_found",
                        new Object[]{uuid},
                        ErrorMessageSource.locale()
                )
        );
    }

    public ResponseStatusException emailDuplicate(String email) {
        return new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                errorMessageSource.getMessage(
                        "userMessage.email_duplicate",
                        new Object[]{email},
                        ErrorMessageSource.locale()
                )
        );
    }


    /* Persist Failed */

    public ResponseStatusException roleInvalid(String role) {
        return new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                errorMessageSource.getMessage(
                        "userMessage.role_invalid",
                        new Object[]{role},
                        ErrorMessageSource.locale()
                )
        );
    }

}
