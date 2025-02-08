package com.yourproduct.your_product.service;

import com.yourproduct.your_product.dto.LoginResponseDto;
import com.yourproduct.your_product.entity.User;

public interface AuthService {
    void signup(User user);

    void confirmEmail(String emailConfirmationToken);

    void resendConfirmationEmail(String email);

    LoginResponseDto login(String email, String password);

    void forgotPassword(String email);

    void resetPassword(String password, String resetToken);
}
