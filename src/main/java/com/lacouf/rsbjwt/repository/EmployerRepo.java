package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, Long> {
    public Employer findEmployeById(Long id);
}
