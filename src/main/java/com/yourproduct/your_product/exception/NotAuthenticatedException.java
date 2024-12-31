package com.yourproduct.your_product.exception;

import org.springframework.http.HttpStatus;

public class NotAuthenticatedException extends CustomException {

    public NotAuthenticatedException() {
        super("You are not authenticated to perform this action", HttpStatus.UNAUTHORIZED);
    }

    public NotAuthenticatedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
