package com.lacouf.rsbjwt.security.exception;

// ceci est un unchecked exception. La convention dit que les exceptions customs devraient être checked.
// Alors, cette exception devrait extend Exception
public class ProfessorNotFoundException extends Exception {
    public ProfessorNotFoundException(Long id) {
        super("Professeur introuvable avec l'id : " + id);
    }
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}