package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
