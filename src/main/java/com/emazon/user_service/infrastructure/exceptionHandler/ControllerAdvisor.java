package com.emazon.user_service.infrastructure.exceptionHandler;

import com.emazon.user_service.domain.exception.*;
import com.emazon.user_service.infrastructure.exception.DocumentAlreadyExistsException;
import com.emazon.user_service.infrastructure.exception.EmailAlreadyExistsException;
import com.emazon.user_service.infrastructure.exception.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(PasswordNotNullException.class)
    public ResponseEntity<Map<String, String>> handlePasswordNullException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.PASSWORD_CANNOT_BE_NULL.getMessage()));
    }

    @ExceptionHandler(IllegalPhoneFormatException.class)
    public ResponseEntity<Map<String, String>> handleIllegalPhoneFormatException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ILLEGAL_PHONE_FORMAT.getMessage()));
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMAIL_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleDocumentAlreadyExistsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.DOCUMENT_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(UserNotNullException.class)
    public ResponseEntity<Map<String, String>> handleUserNotNull() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_CANNOT_BE_NULL.getMessage()));
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmailFormatException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_EMAIL_FORMAT.getMessage()));
    }

    @ExceptionHandler(NotAdultException.class)
    public ResponseEntity<Map<String, String>> handleNotAdultException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_MUST_BE_ADULT.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CREDENTIALS.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.USER_NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDocumentException() {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_DOCUMENT.getMessage()));
    }
}
