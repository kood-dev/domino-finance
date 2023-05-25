package com.domino.finance.common.exception;

import static com.domino.finance.common.exception.ExceptionType.UNEXPECTED;

public class ExternalClientCanNotProceedException extends BusinessException {
    private final static ExceptionType EXCEPTION_TYPE = UNEXPECTED;
    public ExternalClientCanNotProceedException() {
        super(EXCEPTION_TYPE, EXCEPTION_TYPE.getMessage());
    }

    public ExternalClientCanNotProceedException(String message) {
        super(UNEXPECTED, message);
    }

}
