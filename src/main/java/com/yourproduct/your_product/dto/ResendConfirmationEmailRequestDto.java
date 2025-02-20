package com.yourproduct.your_product.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResendConfirmationEmailRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    String email;
}
