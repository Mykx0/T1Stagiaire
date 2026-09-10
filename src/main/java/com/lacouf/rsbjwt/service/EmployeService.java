package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Compagnie;
import com.lacouf.rsbjwt.model.Employe;
import com.lacouf.rsbjwt.repository.EmployeSpringRepo;
import com.lacouf.rsbjwt.service.dto.EmployeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeService {

    private EmployeSpringRepo employeSpringRepo;

    public EmployeService(EmployeSpringRepo employeSpringRepo){
        this.employeSpringRepo = employeSpringRepo;
    }

    @Transactional
    public EmployeDTO addEmploye(Long id, String name, String surname, String email, String password, Compagnie compagnie){
        Employe employe = new Employe();
        employe.setId(id);
        employe.setName(name);
        employe.setSurname(surname);
        employe.setEmail(email);
        employe.setPassword(password);
        employe.setCompagnie(compagnie);
        return EmployeDTO.of((Employe) employeSpringRepo.save(employe));
    }

    @Transactional
    public EmployeDTO findEmployeBy_Id(Long id){
        return EmployeDTO.of((Employe) employeSpringRepo.findEmployeBy_id(id));
    }
}
