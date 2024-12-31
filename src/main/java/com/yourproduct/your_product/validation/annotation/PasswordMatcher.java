package com.yourproduct.your_product.validation.annotation;

import com.yourproduct.your_product.validation.validator.PasswordsMatcherValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordsMatcherValidator.class)
public @interface PasswordMatcher {
    String message() default "Passwords doesn't match.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
