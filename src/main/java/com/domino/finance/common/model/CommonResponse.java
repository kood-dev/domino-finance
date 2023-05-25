package com.domino.finance.common.model;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> {
    @NonNull
    private String code;
    @NonNull
    private String message;
    private T data;

    private CommonResponse(@NonNull String code, @NonNull String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static CommonResponse ok() {
        return ok(null);
    }

    public static <T> CommonResponse<T> ok(final T data) {
        return new CommonResponse(Integer.toString(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), data);
    }


}
