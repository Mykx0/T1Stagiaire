package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.*;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.service.EmployerService;
import com.lacouf.rsbjwt.service.dto.CompanyDTO;
import com.lacouf.rsbjwt.service.dto.EmployerDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class ReactSpringSecurityJwtApplication implements CommandLineRunner {
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployerService employerService;

    public ReactSpringSecurityJwtApplication(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, EmployerService employerService) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.employerService = employerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactSpringSecurityJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        final Optional<UserApp> userAppByEmail = userAppRepository.findUserAppByEmail("l@l.com");
        userAppByEmail.ifPresent(userApp -> System.out.println("user " + userAppByEmail));

        CompanyDTO compagnie = employerService.addCompagnie("Hydroquebec", "Montreal");
        CompanyDTO compagnie2 = employerService.addCompagnie("Videotron" , "Laval");

        EmployerDTO employe1 = employerService.addEmploye( "Cassie", "Cage", "cassiecage@gmail.com", "jiji", compagnie.id());
        EmployerDTO employe2 = employerService.addEmploye( "Jonny", "Cage", "jhonndhjfhfod", "jojo", compagnie.id());

        EmployerDTO trouve = employerService.findEmployeBy_Id(employe1.id());
        EmployerDTO trouve2 = employerService.findEmployeBy_Id(employe2.id());

        System.out.println(compagnie);
        System.out.println(compagnie2);
        System.out.println(trouve);
        System.out.println(trouve2);
    }
}