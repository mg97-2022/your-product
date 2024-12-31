package com.yourproduct.your_product.exception;

import org.springframework.http.HttpStatus;

public class NotAuthorizedException extends CustomException {
    public NotAuthorizedException() {
        super("You are not authorized to perform this action", HttpStatus.FORBIDDEN);
    }

    public NotAuthorizedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
