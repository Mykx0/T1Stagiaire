package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}