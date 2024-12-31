package com.yourproduct.your_product.validation.validator;

import com.yourproduct.your_product.validation.annotation.PasswordMatcher;
import com.yourproduct.your_product.validation.interfaces.PasswordConfirmable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatcherValidator implements ConstraintValidator<PasswordMatcher, PasswordConfirmable> {
    @Override
    public boolean isValid(PasswordConfirmable passwordConfirmable, ConstraintValidatorContext context) {
        if (passwordConfirmable.getPassword() == null || passwordConfirmable.getPasswordConfirmation() == null)
            return false;
        return passwordConfirmable.getPassword().equals(passwordConfirmable.getPasswordConfirmation());
    }
}
