package com.yourproduct.your_product.useCase.impl;

import com.yourproduct.your_product.dto.LoginRequestDto;
import com.yourproduct.your_product.dto.LoginResponseDto;
import com.yourproduct.your_product.dto.SignupRequestDto;
import com.yourproduct.your_product.entity.User;
import com.yourproduct.your_product.mapper.AuthMapper;
import com.yourproduct.your_product.service.AuthService;
import com.yourproduct.your_product.useCase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUseCaseImpl implements AuthUseCase {
    private final AuthService authService;
    private final AuthMapper authMapper;

    @Override
    public void signup(SignupRequestDto signupRequest) {
        User user = authMapper.toUserEntityFromSignup(signupRequest);
        authService.signup(user);
    }

    @Override
    public void confirmEmail(String emailConfirmationToken) {
        authService.confirmEmail(emailConfirmationToken);
    }

    @Override
    public void resendConfirmationEmail(String email) {
        authService.resendConfirmationEmail(email);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto.email(), loginRequestDto.password());
    }

    @Override
    public void forgotPassword(String email) {
        authService.forgotPassword(email);
    }

    @Override
    public void resetPassword(String password, String resetToken) {
        authService.resetPassword(password, resetToken);
    }
}
