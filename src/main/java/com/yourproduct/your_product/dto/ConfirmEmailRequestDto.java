package com.yourproduct.your_product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConfirmEmailRequestDto {
    @NotBlank(message = "Please provide confirmation token")
    String confirmToken;
}
