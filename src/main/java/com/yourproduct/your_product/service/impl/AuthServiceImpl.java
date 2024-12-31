package com.yourproduct.your_product.service.impl;

import com.yourproduct.your_product.dto.LoginResponseDto;
import com.yourproduct.your_product.dto.UserDto;
import com.yourproduct.your_product.entity.User;
import com.yourproduct.your_product.enums.UserTokenTypes;
import com.yourproduct.your_product.exception.CustomException;
import com.yourproduct.your_product.exception.InvalidDataException;
import com.yourproduct.your_product.exception.NotAuthorizedException;
import com.yourproduct.your_product.mapper.UserMapper;
import com.yourproduct.your_product.security.JwtUtil;
import com.yourproduct.your_product.service.AuthService;
import com.yourproduct.your_product.service.UserService;
import com.yourproduct.your_product.utils.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom;
    private final UserService userService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String otp = saveConfirmEmailOtp(user);
        // Send confirmation email
        sendConfirmationEmail(user.getEmail(), otp);
    }

    @Override
    public void confirmEmail(String emailConfirmationToken) {
        User user = userService.findUserByToken(emailConfirmationToken, UserTokenTypes.CONFIRM_EMAIL);
        if (user.getEnabled()) {
            throw new InvalidDataException("You already confirmed your email");
        }
        user.setEnabled(true);
        user.setToken(null);
        user.setTokenType(null);
        user.setTokenExpiresAt(null);
        userService.updateUser(user);
    }

    @Override
    public void resendConfirmationEmail(String email) {
        User user = userService.findUserByEmail(email);
        if (user.getEnabled()) throw new InvalidDataException("You already confirmed your email");
        String otp = saveConfirmEmailOtp(user);
        // Send confirmation email
        sendConfirmationEmail(user.getEmail(), otp);
    }

    @Override
    public LoginResponseDto login(String email, String password) {
        // 1) Check if password and email matches
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new InvalidDataException("Invalid email or password.");
        } catch (LockedException e) {
            throw new NotAuthorizedException("This account is locked.");
        } catch (DisabledException e) {
            throw new NotAuthorizedException("Please confirm your email first.");
        }
        User user = userService.findUserByEmail(email);
        String accessToken = jwtUtil.createToken(user.getEmail());
        UserDto userDto = userMapper.toDto(user);
        return LoginResponseDto.builder().accessToken(accessToken).user(userDto).build();
    }

    @Override
    public void forgotPassword(String email) {
        User user = userService.findUserByEmail(email);
        if (user.getLocked()) {
            throw new NotAuthorizedException("This account is locked.");
        } else if (!user.getEnabled()) {
            throw new NotAuthorizedException("Please confirm your email first.");
        }
        String otp = generateSecureOTP();
        user.setToken(otp);
        user.setTokenType(UserTokenTypes.RESET_PASSWORD);
        user.setTokenExpiresAt(LocalDateTime.now().plusMinutes(15));
        userService.saveUser(user);
        // Send email
        Map<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("resetPasswordOtp", otp);
        emailService.send(email, "reset-password", "Reset Password", emailVariables);
    }

    @Override
    public void resetPassword(String password, String resetToken) {
        User user = userService.findUserByToken(resetToken, UserTokenTypes.RESET_PASSWORD);
        user.setPassword(passwordEncoder.encode(password));
        user.setToken(null);
        user.setTokenType(null);
        user.setTokenExpiresAt(null);
        userService.updateUser(user);
    }

    /************************************** HELPER METHODS *********************************************/
    private String generateSecureOTP() {
        int otp = 100000 + secureRandom.nextInt(900000); // Ensures a 6-digit number
        return String.valueOf(otp);
    }

    private String saveConfirmEmailOtp(User user) {
        String otp = generateSecureOTP();
        user.setToken(otp);
        user.setTokenType(UserTokenTypes.CONFIRM_EMAIL);
        user.setTokenExpiresAt(LocalDateTime.now().plusMinutes(15));
        userService.saveUser(user);
        return otp;
    }

    private void sendConfirmationEmail(String userEmail, String otp) {
        Map<String, Object> emailVariables = new HashMap<>();
        emailVariables.put("confirmEmailOtp", otp);
        emailService.send(userEmail, "confirm-email", "Confirm your email", emailVariables);
    }
}
