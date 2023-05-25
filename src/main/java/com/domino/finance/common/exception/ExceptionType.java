package com.domino.finance.common.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    // standard
    BAD_REQUEST("301", HttpStatus.BAD_REQUEST.value(), "exception.standard.badRequest"),
    UNAUTHORIZED("302", HttpStatus.UNAUTHORIZED.value(), "exception.standard.unauthorized"),
    FORBIDDEN("303", HttpStatus.FORBIDDEN.value(), "exception.standard.forbidden"),
    NOT_FOUND("304", HttpStatus.NOT_FOUND.value(), "exception.standard.notFound"),
    UNEXPECTED("305", HttpStatus.INTERNAL_SERVER_ERROR.value(), "exception.standard.unexpected")
    ;

    @NonNull
    private final String code;
    private final int status;
    private final String messageCode;

    public String getMessage() {
        return messageCode;
    }
}
