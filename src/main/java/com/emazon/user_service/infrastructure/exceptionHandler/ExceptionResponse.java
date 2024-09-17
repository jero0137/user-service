package com.emazon.user_service.infrastructure.exceptionHandler;

public enum ExceptionResponse {
    EMAIL_ALREADY_EXISTS("Email already exists"),
    DOCUMENT_ALREADY_EXISTS("Document already exists"),

    INVALID_EMAIL_FORMAT("Invalid email format"),

    PASSWORD_CANNOT_BE_NULL("Password cannot be null"),
    ILLEGAL_PHONE_FORMAT("Phone number must start with '+' and with a minimum of 10 maximum of 13 characters"),

    USER_CANNOT_BE_NULL("User cannot be null"),

    USER_MUST_BE_ADULT("User must be adult");

    private final String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
