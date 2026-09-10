package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.*;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class ReactSpringSecurityJwtApplication implements CommandLineRunner {
    // Brahim El khazraji UwU
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    public ReactSpringSecurityJwtApplication( UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringSecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        final Optional<UserApp> userAppByEmail = userAppRepository.findUserAppByEmail("l@l.com");
        userAppByEmail.ifPresent(userApp -> System.out.println("user " + userAppByEmail));
    }
}
