package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Credentials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends UserApp {

    @Column(nullable = false)
    private Discipline discipline;

    public Professor() {
    }

    public Professor(
            String firstName,
            String lastName,
            Credentials credentials,
            Discipline discipline
    ) {
        super(firstName, lastName, credentials);
        this.discipline = discipline;
    }

    public Discipline getDiscipline() { return discipline; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }
}