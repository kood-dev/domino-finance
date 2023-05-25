package com.domino.finance.common.model;

import com.domino.finance.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class CommonErrorResponse {
    @NonNull
    private String code;
    @NonNull
    private String message;

    public static CommonErrorResponse of(BusinessException businessException) {
        return new CommonErrorResponse(
                businessException.getExceptionType().getCode(),
                businessException.getMessage()
        );
    }
}
