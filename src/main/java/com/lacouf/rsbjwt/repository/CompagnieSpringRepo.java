package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.Compagnie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnieSpringRepo extends JpaRepository<Compagnie, Long> {
}