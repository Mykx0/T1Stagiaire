package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("""
        select p from Professor p
        where trim(lower(p.credentials.email)) = :email
    """)
    Optional<Professor> findByEmail(@Param("email") String email);
}