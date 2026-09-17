package com.lacouf.rsbjwt.model;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("Emp")
public class Employer extends Utilisateur{

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compagnie_id")
    private Company company;

    public Company getCompagnie() {
        return company;
    }

    public void setCompagnie(Company company) {
        this.company = company;
    }

    public Employer(String name, String surname, String email, String password, Company company) {
        super(name, surname, email, password);
        this.company = company;
    }
    public Employer(){}
}
