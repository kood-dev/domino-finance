package com.domino.finance.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidRangeType implements CommonCodeType {
    ONE_DAY("ONE_DAY", "1d"),
    FIVE_DAY("FIVE_DAY", "5d");

    private final String code;
    private final String desc;

}
