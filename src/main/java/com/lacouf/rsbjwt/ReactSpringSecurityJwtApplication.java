package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.repository.StudentRepository;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ReactSpringSecurityJwtApplication implements CommandLineRunner {
    // Edouard Lambert
    private final UserAppRepository userAppRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ReactSpringSecurityJwtApplication( UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, StudentRepository studentRepository, UserRepository userRepository) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringSecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        UserService userService = new UserService(studentRepository, userRepository);
        var student= userService.createStudent("Test","Test","Test@test.com", "test", Discipline.ComputerScience);
        IO.println(student.toString());
    }
}
