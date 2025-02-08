package com.yourproduct.your_product.controller;

import com.yourproduct.your_product.dto.*;
import com.yourproduct.your_product.useCase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUseCase authUseCase;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        authUseCase.signup(signupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/confirm-email")
    public ResponseEntity<Void> confirmEmail(@Valid @RequestBody ConfirmEmailRequestDto confirmEmailRequestDto) {
        authUseCase.confirmEmail(confirmEmailRequestDto.getConfirmToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/resend-confirmation-email")
    public ResponseEntity<Void> resendConfirmationEmail(
            @Valid @RequestBody ResendConfirmationEmailRequestDto resendConfirmationEmailRequestDto) {
        authUseCase.resendConfirmationEmail(resendConfirmationEmailRequestDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authUseCase.login(loginRequestDto));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDto forgotPasswordRequestDto) {
        authUseCase.forgotPassword(forgotPasswordRequestDto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {
        authUseCase.resetPassword(resetPasswordRequestDto.getPassword(), resetPasswordRequestDto.getToken());
        return ResponseEntity.ok().build();
    }
}
