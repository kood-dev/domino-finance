package com.domino.finance.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IntervalType implements CommonCodeType {
    DAY("DAY", "1d");

    private final String code;
    private final String desc;
}
