package com.lacouf.rsbjwt.security.exception;

public class ProfessorNotFoundException extends RuntimeException {
    public ProfessorNotFoundException(Long id) {
        super("Professeur introuvable avec l'id : " + id);
    }
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}