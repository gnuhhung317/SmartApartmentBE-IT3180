package com.hust.smart_apartment.utils;

import com.hust.smart_apartment.annotations.CustomSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeValidator implements ConstraintValidator<CustomSize, String> {

    private int min;
    private int max;

    @Override
    public void initialize(CustomSize constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(s);
        if (s == null) {
            return true;
        }
        return s.length() >= min && s.length() <= max;
    }
}
