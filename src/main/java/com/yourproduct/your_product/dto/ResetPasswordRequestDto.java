package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.utils.GeneralMessages;
import com.yourproduct.your_product.utils.Regex;
import com.yourproduct.your_product.validation.annotation.PasswordMatcher;
import com.yourproduct.your_product.validation.interfaces.PasswordConfirmable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@PasswordMatcher
public class ResetPasswordRequestDto implements PasswordConfirmable {
    @NotBlank(message = "Please enter your password.")
    @Pattern(regexp = Regex.PASSWORD_PATTERN, message = GeneralMessages.PASSWORD_ERROR_MESSAGE)
    private String password;

    private String passwordConfirmation;

    @NotBlank(message = "Confirm token is required.")
    private String token;
}