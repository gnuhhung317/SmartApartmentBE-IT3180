package com.hust.smart_apartment.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface QuickSearchDomain {
    String tableName() default "";
    boolean isCustom() default false;
}