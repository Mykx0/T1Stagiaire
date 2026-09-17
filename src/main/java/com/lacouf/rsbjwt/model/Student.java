package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Role;
import jakarta.persistence.Entity;

@Entity
public class Student extends User {
    private Discipline discipline;
    public Student(String firstName, String lastName, String email, String password, Discipline discipline) {
        super(firstName, lastName, email, password, Role.STUDENT);
        this.discipline = discipline;
    }

    public Student() {
    }
}
