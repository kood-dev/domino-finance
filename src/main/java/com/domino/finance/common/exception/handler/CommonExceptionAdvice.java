package com.domino.finance.common.exception.handler;

import com.domino.finance.common.exception.BusinessException;
import com.domino.finance.common.exception.ExternalClientCanNotProceedException;
import com.domino.finance.common.model.CommonErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(value = {ExternalClientCanNotProceedException.class})
    protected ResponseEntity<CommonErrorResponse> handleException(ExternalClientCanNotProceedException externalClientCanNotProceedException) {
        log.error("[Exception] External client exception occurred");
        return ResponseEntity.status(externalClientCanNotProceedException.getExceptionType().getStatus())
                .body(CommonErrorResponse.of(BusinessException.of(externalClientCanNotProceedException.getExceptionType(), externalClientCanNotProceedException.getMessage())));
    }

}
