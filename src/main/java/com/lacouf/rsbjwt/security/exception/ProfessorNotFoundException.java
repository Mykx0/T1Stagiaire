package com.lacouf.rsbjwt.security.exception;

public class ProfessorNotFoundException extends Exception {
    public ProfessorNotFoundException(Long id) {
        super("Professeur introuvable avec l'id : " + id);
    }
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}