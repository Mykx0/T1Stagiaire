package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("""
        select p from Professor p
        where trim(lower(p.credentials.email)) = :email
    """)
    Optional<Professor> findByEmail(@Param("email") String email);

    @Query("""
        select p from Professor p
        where lower(p.firstName) = lower(:firstName)
          and lower(p.lastName) = lower(:lastName)
    """)
    List<Professor> findByFullName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
    );
}