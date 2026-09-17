package com.lacouf.rsbjwt;

import com.lacouf.rsbjwt.model.Compagnie;
import com.lacouf.rsbjwt.model.Employe;
import com.lacouf.rsbjwt.repository.CompagnieSpringRepo;
import com.lacouf.rsbjwt.repository.EmployeSpringRepo;
import com.lacouf.rsbjwt.service.EmployeService;
import com.lacouf.rsbjwt.service.dto.CompagnieDTO;
import com.lacouf.rsbjwt.service.dto.EmployeDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeServiceTest {

    @Mock
    private EmployeSpringRepo employeSpringRepo;

    @Mock
    private CompagnieSpringRepo compagnieSpringRepo;

    @InjectMocks
    private EmployeService employeService;


    @Test
    void addCompagnie_devraitCreerUneCompagnie() {

        Compagnie compagnie = new Compagnie(
                1L,
                "Google",
                "Montreal"
        );

        when(compagnieSpringRepo.save(any(Compagnie.class)))
                .thenReturn(compagnie);

        CompagnieDTO resultat =
                employeService.addCompagnie("Google", "Montreal");

        assertNotNull(resultat);
        assertEquals(1L, resultat.id());
        assertEquals("Google", resultat.compagniName());
        assertEquals("Montreal", resultat.city());

        verify(compagnieSpringRepo, times(1))
                .save(any(Compagnie.class));
    }


    @Test
    void addEmploye_devraitCreerUnEmploye() {

        Compagnie compagnie = new Compagnie(
                1L,
                "Google",
                "Montreal"
        );

        Employe employe = new Employe(
                10L,
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                compagnie
        );

        when(compagnieSpringRepo.getReferenceById(1L))
                .thenReturn(compagnie);

        when(employeSpringRepo.save(any(Employe.class)))
                .thenReturn(employe);

        EmployeDTO resultat = employeService.addEmploye(
                10L,
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                1L
        );

        assertNotNull(resultat);
        assertEquals(10L, resultat.id());
        assertEquals("Brahim", resultat.name());
        assertEquals("El Khazraji", resultat.surname());
        assertEquals("brahim@gmail.com", resultat.email());
        assertEquals("1234", resultat.password());
        assertEquals(compagnie, resultat.compagnie());

        verify(compagnieSpringRepo, times(1))
                .getReferenceById(1L);

        verify(employeSpringRepo, times(1))
                .save(any(Employe.class));
    }


    @Test
    void findEmployeById_devraitRetournerEmploye() {

        Compagnie compagnie = new Compagnie(
                1L,
                "Google",
                "Montreal"
        );

        Employe employe = new Employe(
                10L,
                "Brahim",
                "El Khazraji",
                "brahim@gmail.com",
                "1234",
                compagnie
        );

        when(employeSpringRepo.findEmployeById(10L))
                .thenReturn(employe);

        EmployeDTO resultat =
                employeService.findEmployeBy_Id(10L);

        assertNotNull(resultat);
        assertEquals(10L, resultat.id());
        assertEquals("Brahim", resultat.name());
        assertEquals("El Khazraji", resultat.surname());
        assertEquals("brahim@gmail.com", resultat.email());
        assertEquals(compagnie, resultat.compagnie());

        verify(employeSpringRepo, times(1))
                .findEmployeById(10L);
    }
}