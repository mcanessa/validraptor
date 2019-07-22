package com.github.mcanessa.validraptor.annotation;

import com.github.mcanessa.validraptor.Validator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ PARAMETER, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    Class<? extends Validator> value();
}
