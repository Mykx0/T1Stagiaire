package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Role;
import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    public Student(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, Role.STUDENT);
    }

    public Student() {
    }
}
