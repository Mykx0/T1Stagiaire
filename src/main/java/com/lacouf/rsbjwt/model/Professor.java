package com.lacouf.rsbjwt.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Prof")
public class Professor extends Utilisateur{
    private String discpline;

    public Professor(Long id , String name ,String surnmae , String email , String password , String discpline){
        super(id , name , surnmae , email , password);
        this.discpline = discpline;
    }

    public String getDiscpline() {
        return discpline;
    }

    public void setDiscpline(String discpline) {
        this.discpline = discpline;
    }
}
