package com.lacouf.rsbjwt.model;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("Emp")
public class Employe extends Utilisateur{

    @ManyToOne(cascade = CascadeType.ALL)
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
