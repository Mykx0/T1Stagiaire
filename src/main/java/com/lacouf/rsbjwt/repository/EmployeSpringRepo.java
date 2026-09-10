package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeSpringRepo extends JpaRepository<Employe , Long> {
    public List<Employe> findEmployeBy_id(Long id);
}
