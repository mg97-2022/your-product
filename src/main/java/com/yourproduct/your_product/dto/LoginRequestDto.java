package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.utils.GeneralMessages;
import com.yourproduct.your_product.utils.Regex;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDto(
        @NotBlank(message = "Email is required") @Email(message = "Please enter a valid email") String email,

        @NotBlank(message = "Password is required")
        @Pattern(regexp = Regex.PASSWORD_PATTERN, message = GeneralMessages.PASSWORD_ERROR_MESSAGE) String password
) {
}
