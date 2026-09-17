package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.service.ProfService;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactSpringSecurityJwtApplication implements CommandLineRunner {

    private final ProfService userService;

    public ReactSpringSecurityJwtApplication(ProfService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringSecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) {

        seedProfessor("Jean", "Tremblay", "jeantremblay@example.com", "password123", "Software Engineering");
        seedProfessor("Marie", "Curie", "mariecurie@example.com", "password123", "Physics");
        seedProfessor("Test", "Test", "testTest@example.com", "test123", "testing");
        seedProfessor("Ada", "Lovelace", "adalovelace@example.com", "password123", "Computer Science");
    }

    private void seedProfessor(String firstName, String lastName, String email,
                               String rawPassword, String discipline) {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setPassword(rawPassword);
        dto.setDiscipline(discipline);

        try {
            ProfessorDTO saved = userService.registerProfessor(dto);
            System.out.println("Professor created: " + saved.getEmail());
        } catch (Exception e) {
            System.out.println("Professor already exists or error: "
                    + email + " (" + e.getMessage() + ")");
        }
    }
}