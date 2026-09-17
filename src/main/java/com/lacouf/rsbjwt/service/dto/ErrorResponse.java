package com.lacouf.rsbjwt.service.dto;

public record ErrorResponse(String error, String message) {

    public static ErrorResponse emailAlreadyUsed(String msg) {
        return new ErrorResponse("Email already used", msg);
    }

    public static ErrorResponse professorNotFound(String msg) {
        return new ErrorResponse("Professor not found", msg);
    }

    public static ErrorResponse invalidInput(String msg) {
        return new ErrorResponse("Invalid input", msg);
    }

    public static ErrorResponse internalError(String msg) {
        return new ErrorResponse("Internal error", msg);
    }
}