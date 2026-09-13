package com.lacouf.rsbjwt.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProfessorNotFoundException extends RuntimeException {

    public ProfessorNotFoundException(Long id) {
        super("Professor not found with id: " + id);
    }
}