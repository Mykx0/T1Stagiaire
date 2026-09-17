package com.lacouf.rsbjwt.service.dto;

import com.lacouf.rsbjwt.model.Company;
import com.lacouf.rsbjwt.model.Employer;

public record EmployerDTO(Long id, String name, String surname, String email, String password, Company company) {
    public static EmployerDTO of(Employer employe) {
        return new EmployerDTO(employe.getId() , employe.getName() , employe.getSurname() , employe.getEmail() , employe.getPassword() ,employe.getCompagnie());
    }
}
