package com.lacouf.rsbjwt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compagniName;
    private String city;

    public Company(String compagniName, String city) {
        this.compagniName = compagniName;
        this.city = city;
    }

    public Company(){}

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCompagniName() {
        return compagniName;
    }
}