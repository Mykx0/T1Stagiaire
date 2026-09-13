package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Credentials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends UserApp {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String specialization;

    public Professor() {
    }

    public Professor(
            String firstName,
            String lastName,
            Credentials credentials,
            String email,
            String department,
            String specialization
    ) {
        super(firstName, lastName, credentials);
        this.email = email;
        this.department = department;
        this.specialization = specialization;
    }
    public String getFirstName() {
        return super.getFirstName();
    }
    public void setFirstName(String firstName) {
        this.setFirstName(firstName);
    }
    public String getLastName() {
        return super.getLastName();
    }
    public void setLastName(String lastName) {
        this.setLastName(lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}