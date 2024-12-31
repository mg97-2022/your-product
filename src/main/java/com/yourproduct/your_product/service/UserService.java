package com.yourproduct.your_product.service;

import com.yourproduct.your_product.entity.User;
import com.yourproduct.your_product.enums.UserTokenTypes;
import com.yourproduct.your_product.exception.InvalidDataException;
import com.yourproduct.your_product.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    @Override
    User loadUserByUsername(String email) throws NotFoundException;

    void saveUser(User user);

    User findUserByToken(String token, UserTokenTypes tokenType) throws InvalidDataException;

    void updateUser(User user);

    User findUserByEmail(String email) throws NotFoundException;
}
