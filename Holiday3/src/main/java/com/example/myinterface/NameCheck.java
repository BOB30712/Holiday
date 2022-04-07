package com.example.myinterface;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = NameCheckValidator.class)
public @interface NameCheck {
 boolean required() default true;
 String message() default "error";
 Class<?>[] groups() default{};
 Class<? extends Payload>[] payload() default{};
 }
