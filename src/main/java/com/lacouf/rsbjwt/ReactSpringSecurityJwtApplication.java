package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.*;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.service.EmployeService;
import com.lacouf.rsbjwt.service.dto.CompagnieDTO;
import com.lacouf.rsbjwt.service.dto.EmployeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class ReactSpringSecurityJwtApplication implements CommandLineRunner {
    // Brahim El khazraji
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeService employeService;

    public ReactSpringSecurityJwtApplication(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, EmployeService employeService) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeService = employeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringSecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        final Optional<UserApp> userAppByEmail = userAppRepository.findUserAppByEmail("l@l.com");
        userAppByEmail.ifPresent(userApp -> System.out.println("user " + userAppByEmail));

        CompagnieDTO compagnie = employeService.addCompagnie("Hydroquebec", "Montreal");

        EmployeDTO employe1 = employeService.addEmploye(null, "Cassie", "Cage", "cassiecage@gmail.com", "jiji", compagnie.id());
        EmployeDTO employe2 = employeService.addEmploye(null, "Jonny", "Cage", "jhonndhjfhfod", "jojo", compagnie.id());

        EmployeDTO trouve = employeService.findEmployeBy_Id(employe1.id());
        EmployeDTO trouve2 = employeService.findEmployeBy_Id(employe2.id());
        System.out.println(trouve);
        System.out.println(trouve2);
    }
}