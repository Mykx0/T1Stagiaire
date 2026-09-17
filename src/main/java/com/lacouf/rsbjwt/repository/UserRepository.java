package com.lacouf.rsbjwt.repository;

import com.lacouf.rsbjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        select u from User u where trim(lower(u.credentials.email)) = :email
    """)
    Optional<User> findUserAppByEmail(@Param("email") String email);
}
