package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.utils.GeneralMessages;
import com.yourproduct.your_product.utils.Regex;
import com.yourproduct.your_product.validation.annotation.PasswordMatcher;
import com.yourproduct.your_product.validation.interfaces.PasswordConfirmable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@PasswordMatcher
public class SignupRequestDto implements PasswordConfirmable {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = Regex.PASSWORD_PATTERN, message = GeneralMessages.PASSWORD_ERROR_MESSAGE)
    private String password;

    private String passwordConfirmation;
}
