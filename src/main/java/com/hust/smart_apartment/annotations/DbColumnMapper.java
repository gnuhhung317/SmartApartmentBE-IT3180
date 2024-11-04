package com.hust.smart_apartment.annotations;

import jakarta.persistence.AttributeConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumnMapper {

    interface None extends AttributeConverter<String, Object> {

    }

    String value() default "";

    @SuppressWarnings("java:S1452")
    Class<? extends AttributeConverter<?, ?>> converter()  default None.class;

}
