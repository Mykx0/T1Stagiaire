package com.lacouf.rsbjwt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Compagnie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compagniName;
    private String city;

    public Compagnie(Long id, String compagniName, String city) {
        this.id = id;
        this.compagniName = compagniName;
        this.city = city;
    }
}
