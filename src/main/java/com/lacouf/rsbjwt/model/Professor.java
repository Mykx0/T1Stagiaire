package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Credentials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Professor extends User {

    @Column(name = "discipline")
    private Discipline discipline;

    public Professor() {
    }

    public Professor(
            String firstName,
            String lastName,
            Credentials credentials,
            Discipline discipline
    ) {
        super(
                firstName,
                lastName,
                credentials.getEmail(),
                credentials.getPassword(),
                credentials.getRole()
        );
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}