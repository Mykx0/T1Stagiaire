package com.lacouf.rsbjwt.service.dto;

import com.lacouf.rsbjwt.model.Compagnie;
import com.lacouf.rsbjwt.model.Employe;

public record EmployeDTO(Long id, String name, String surname, String email, String password, Compagnie compagnie) {
    public static EmployeDTO of(Employe employe) {
        return new EmployeDTO(employe.getId() , employe.getName() , employe.getSurname() , employe.getEmail() , employe.getPassword() ,employe.getCompagnie());
    }
}
