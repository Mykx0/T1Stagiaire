package com.lacouf.rsbjwt.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
// ceci est un unchecked exception. La convention dit que les exceptions customs devraient être checked.
// Alors, cette exception devrait extend Exception
public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException(String email) {
        super("Email already used: " + email);
    }
}