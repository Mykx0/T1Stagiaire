package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.Company;
import com.lacouf.rsbjwt.model.Employer;
import com.lacouf.rsbjwt.repository.CompagnyRepo;
import com.lacouf.rsbjwt.repository.EmployerRepo;
import com.lacouf.rsbjwt.service.EmployerService;
import com.lacouf.rsbjwt.service.dto.CompanyDTO;
import com.lacouf.rsbjwt.service.dto.EmployerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployerServiceTest {

    @Mock
    private EmployerRepo employerRepo;

    @Mock
    private CompagnyRepo compagnyRepo;

    @InjectMocks
    private EmployerService employerService;


    @Test
    void addCompagnie_devraitCreerUneCompagnie() {

        Company company = new Company(
                "Google",
                "Montreal"
        );

        when(compagnyRepo.save(any(Company.class)))
                .thenReturn(company);

        CompanyDTO resultat =
                employerService.addCompagnie("Google", "Montreal");

        assertNotNull(resultat);
        assertEquals(1L, resultat);
        assertEquals("Google", resultat.compagniName());
        assertEquals("Montreal", resultat.city());

        verify(compagnyRepo, times(1))
                .save(any(Company.class));
    }


    @Test
    void addEmploye_devraitCreerUnEmploye() {

        Company company = new Company(
                "Google",
                "Montreal"
        );

        Employer employe = new Employer(
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                company
        );

        when(compagnyRepo.getReferenceById(1L))
                .thenReturn(company);

        when(employerRepo.save(any(Employer.class)))
                .thenReturn(employe);

        EmployerDTO resultat = employerService.addEmploye(
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                1L
        );

        assertNotNull(resultat);
        assertEquals(1L, resultat.id());
        assertEquals("Brahim", resultat.name());
        assertEquals("El Khazraji", resultat.surname());
        assertEquals("brahim@gmail.com", resultat.email());
        assertEquals("1234", resultat.password());
        assertEquals(company, resultat.company());

        verify(compagnyRepo, times(1))
                .getReferenceById(1L);

        verify(employerRepo, times(1))
                .save(any(Employer.class));
    }


    @Test
    void findEmployeById_devraitRetournerEmploye() {

        Company company = new Company(
                "Google",
                "Montreal"
        );

        Employer employe = new Employer(
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                company
        );

        when(employerRepo.findEmployeById(1L))
                .thenReturn(employe);

        EmployerDTO resultat =
                employerService.findEmployeBy_Id(10L);

        assertNotNull(resultat);
        assertEquals(10L, resultat.id());
        assertEquals("Brahim", resultat.name());
        assertEquals("El Khazraji", resultat.surname());
        assertEquals("brahim@gmail.com", resultat.email());
        assertEquals(company, resultat.company());

        verify(employerRepo, times(1))
                .findEmployeById(10L);
    }
}