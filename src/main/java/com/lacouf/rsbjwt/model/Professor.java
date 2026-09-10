package com.lacouf.rsbjwt.model;

import com.lacouf.rsbjwt.model.auth.Credentials;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "professors")
@PrimaryKeyJoinColumn(name = "id")
public class Professor extends UserApp {

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
            String department,
            String specialization
    ) {
        super(firstName, lastName, credentials);
        this.department = department;
        this.specialization = specialization;
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