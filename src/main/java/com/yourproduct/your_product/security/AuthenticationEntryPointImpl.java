package com.yourproduct.your_product.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourproduct.your_product.exception.ExceptionResponseDTO;
import com.yourproduct.your_product.exception.ExceptionStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request, HttpServletResponse response,
            org.springframework.security.core.AuthenticationException authException) throws IOException,
            ServletException {
        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message("Authentication token is missing or invalid.")
                                    .status(ExceptionStatus.FAIL.toString())
                                    .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                    .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        String responseBody = objectMapper.writeValueAsString(exceptionResponse);
        writer.write(responseBody);
        writer.flush();
        writer.close();
    }
}