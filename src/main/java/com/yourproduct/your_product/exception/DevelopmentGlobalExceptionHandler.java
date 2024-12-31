package com.yourproduct.your_product.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@ControllerAdvice
@RequiredArgsConstructor
public class DevelopmentGlobalExceptionHandler {
    private final ExceptionUtils exceptionUtils;

    // Handles validation errors messages for annotations (@Valid)
    // For the response message, it will be an array of messages
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorMessages.add(errorMessage);
        });

        HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessages).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // Handles duplicate violation constraints that are thrown from db
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {
        HttpStatus statusCode = HttpStatus.CONFLICT;

        String errorMessage = exceptionUtils.getDataIntegrityViolationMessage(ex.getMostSpecificCause().getMessage());

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(errorMessage).status(ExceptionStatus.FAIL.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // Handles my custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCustomException(CustomException ex) {
        HttpStatus statusCode = ex.getStatusCode();

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(ex.getMessage()).status(ex.getStatus())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }

    // If the exception is not of the above exception types, then this will be returned
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        ExceptionResponseDTO exceptionResponse =
                ExceptionResponseDTO.builder().message(ex.getMessage()).status(ExceptionStatus.ERROR.toString())
                                    .error(statusCode.getReasonPhrase()).path(ex.getStackTrace()[0].toString())
                                    .exception(ex.toString()).build();

        return new ResponseEntity<>(exceptionResponse, statusCode);
    }
}
