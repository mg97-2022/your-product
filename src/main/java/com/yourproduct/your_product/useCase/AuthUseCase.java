package com.yourproduct.your_product.useCase;

import com.yourproduct.your_product.dto.LoginRequestDto;
import com.yourproduct.your_product.dto.LoginResponseDto;
import com.yourproduct.your_product.dto.SignupRequestDto;

public interface AuthUseCase {
    void signup(SignupRequestDto signupRequestDto);

    void confirmEmail(String emailConfirmationToken);

    void resendConfirmationEmail(String email);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    void forgotPassword(String email);

    void resetPassword(String password, String resetToken);
}
