package com.domino.finance.common.anotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValid, Enum<?>> {
    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) return false;

        Object[] enumValues = value.getClass().getEnumConstants();
        return Arrays.stream(enumValues).anyMatch(e -> e == value);
    }
}
