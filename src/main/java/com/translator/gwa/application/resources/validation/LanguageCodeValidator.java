package com.translator.gwa.application.resources.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LanguageCodeValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface LanguageCodeValidator {

    String message() default "Invalid language code. [code]=${value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
