package com.yourproduct.your_product.dto;

import jakarta.validation.constraints.NotBlank;

public record ConfirmEmailRequestDto(
        @NotBlank(message = "Please provide confirmation token") String confirmToken) {}
