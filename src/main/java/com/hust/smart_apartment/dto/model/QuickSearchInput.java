package com.hust.smart_apartment.dto.model;

import com.hust.smart_apartment.constants.QuickSearchKeyOption;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuickSearchInput {

    String columnName() default "";

    QuickSearchKeyOption keyOption() default QuickSearchKeyOption.EQUAL;

    boolean caseInsensitive() default true;
}
