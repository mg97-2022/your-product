package com.yourproduct.your_product.utils.email;

import com.yourproduct.your_product.exception.CustomException;

import java.util.Map;

public interface EmailService {
    void send(String to, String templateName, String subject, Map<String, Object> variables) throws CustomException;
}
