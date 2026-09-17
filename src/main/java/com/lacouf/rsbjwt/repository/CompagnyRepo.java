package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnyRepo extends JpaRepository<Company, Long> {
}