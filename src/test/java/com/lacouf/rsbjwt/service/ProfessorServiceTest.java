package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import com.lacouf.rsbjwt.repository.ProfessorRepository;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.security.exception.ProfessorNotFoundException;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfessorServiceTest {

    @Mock private UserAppRepository userAppRepository;
    @Mock private ProfessorRepository professorRepository;
    @Mock private PasswordEncoder passwordEncoder;

    private ProfService profService;

    private Professor professor;

    @BeforeEach
    void setUp() throws Exception {
        profService = new ProfService(
                userAppRepository,
                professorRepository,
                passwordEncoder,
                new ProfessorDTO.Mapper()
        );

        Credentials credentials = new Credentials(
                "jean.tremblay@example.com", "encodedPassword", Role.PROFESSOR);

        professor = new Professor("Jean", "Tremblay", credentials, Discipline.ComputerScience);

        Field idField = professor.getClass().getSuperclass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(professor, 1L);
    }

    @Test
    void registerProfessor_shouldSaveAndReturnDto() throws EmailAlreadyUsedException {
        ProfessorDTO request = new ProfessorDTO(
                null, "Jean", "Tremblay",
                "jean.tremblay@example.com",
                Discipline.ElectricalEngineering,
                Role.PROFESSOR,
                "password123");

        when(userAppRepository.findUserAppByEmail("jean.tremblay@example.com"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(professorRepository.save(any(Professor.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        ProfessorDTO result = profService.registerProfessor(request);

        assertThat(result).isNotNull();
        assertThat(result.email()).isEqualTo("jean.tremblay@example.com");
        assertThat(result.discipline()).isEqualTo(Discipline.ElectricalEngineering);

        verify(userAppRepository).findUserAppByEmail("jean.tremblay@example.com");
        verify(passwordEncoder).encode("password123");
        verify(professorRepository).save(any(Professor.class));
    }

    @Test
    void registerProfessor_shouldNormalizeEmail() throws EmailAlreadyUsedException {
        ProfessorDTO request = new ProfessorDTO(
                null, "LeBlanc", "Jean",
                "  LEBLANC.JEAN@EXAMPLE.COM  ",
                Discipline.ElectricalEngineering,
                Role.PROFESSOR,
                "password123");

        when(userAppRepository.findUserAppByEmail("leblanc.jean@example.com"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(professorRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        profService.registerProfessor(request);

        verify(userAppRepository).findUserAppByEmail("leblanc.jean@example.com");
    }

    @Test
    void registerProfessor_shouldThrowIfEmailAlreadyUsed() {
        ProfessorDTO request = new ProfessorDTO(
                null, "Jean", "Tremblay",
                "jean.tremblay@example.com",
                Discipline.ElectricalEngineering,
                Role.PROFESSOR,
                "password123");

        when(userAppRepository.findUserAppByEmail("jean.tremblay@example.com"))
                .thenReturn(Optional.of(professor));

        assertThatThrownBy(() -> profService.registerProfessor(request))
                .isInstanceOf(EmailAlreadyUsedException.class);

        verify(professorRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(anyString());
    }


}