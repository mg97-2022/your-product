package com.yourproduct.your_product.service.impl;

import com.yourproduct.your_product.entity.User;
import com.yourproduct.your_product.enums.UserTokenTypes;
import com.yourproduct.your_product.exception.CustomException;
import com.yourproduct.your_product.exception.InvalidDataException;
import com.yourproduct.your_product.exception.NotFoundException;
import com.yourproduct.your_product.repository.UserRepository;
import com.yourproduct.your_product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User"));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByToken(String token, UserTokenTypes tokenType) throws InvalidDataException {
        User user = userRepository.findByToken(token).orElseThrow(() -> new InvalidDataException("Invalid token"));
        if (!tokenType.equals(user.getTokenType())) {
            throw new CustomException("Invalid token", HttpStatus.BAD_REQUEST);
        } else if (user.getTokenExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("Token is expired");
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User"));
    }
}
