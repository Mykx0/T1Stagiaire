package com.lacouf.rsbjwt.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Emp")
public class Employe extends Utilisateur{

    @ManyToOne
    @JoinColumn(name = "compagnie_id")
    private Compagnie compagnie;

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Employe(Long id, String name, String surname, String email, String password, Compagnie compagnie) {
        super(id, name, surname, email, password);
        this.compagnie = compagnie;
    }
    public Employe(){}

}
