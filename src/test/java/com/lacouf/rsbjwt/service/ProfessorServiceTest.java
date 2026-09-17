package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.Mapper.ProfessorMapper;
import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import com.lacouf.rsbjwt.repository.ProfessorRepository;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.security.JwtTokenProvider;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.security.exception.ProfessorNotFoundException;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Mock private AuthenticationManager authenticationManager;
    @Mock private JwtTokenProvider jwtTokenProvider;
    @Mock private UserAppRepository userAppRepository;
    @Mock private ProfessorRepository professorRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private ProfessorMapper professorMapper;

    @InjectMocks private ProfService userService;

    private Professor professor;
    private Credentials credentials;
    private ProfessorDTO dto;

    @BeforeEach
    void setUp() throws Exception {
        credentials = new Credentials("jean.tremblay@example.com",
                "encodedPassword", Role.PROFESSOR);

        professor = new Professor("Jean", "Tremblay", credentials, "Software Engineering");

        Field idField = professor.getClass().getSuperclass().getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(professor, 1L);

        dto = new ProfessorDTO();
        dto.setFirstName("Jean");
        dto.setLastName("Tremblay");
        dto.setEmail("jean.tremblay@example.com");
        dto.setPassword("password123");
        dto.setDiscipline("Software Engineering");
    }

    @Test
    void registerProfessor_shouldSaveAndReturnDto() {
        when(userAppRepository.findUserAppByEmail("jean.tremblay@example.com"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(professorMapper.toEntity(any(ProfessorDTO.class), any(Credentials.class)))
                .thenReturn(professor);
        when(professorRepository.save(professor)).thenReturn(professor);
        when(professorMapper.toDto(professor)).thenReturn(dto);

        ProfessorDTO result = userService.registerProfessor(dto);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("jean.tremblay@example.com");
        assertThat(result.getDiscipline()).isEqualTo("Software Engineering");

        verify(userAppRepository).findUserAppByEmail("jean.tremblay@example.com");
        verify(passwordEncoder).encode("password123");
        verify(professorRepository).save(professor);
        verify(professorMapper).toDto(professor);
    }

    @Test
    void registerProfessor_shouldNormalizeEmail() {
        dto.setEmail("  JEAN.TREMBLAY@EXAMPLE.COM  ");

        when(userAppRepository.findUserAppByEmail("jean.tremblay@example.com"))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(professorMapper.toEntity(any(), any())).thenReturn(professor);
        when(professorRepository.save(any())).thenReturn(professor);
        when(professorMapper.toDto(any())).thenReturn(dto);

        userService.registerProfessor(dto);

        verify(userAppRepository).findUserAppByEmail("jean.tremblay@example.com");
    }

    @Test
    void registerProfessor_shouldThrowIfEmailAlreadyUsed() {
        when(userAppRepository.findUserAppByEmail("jean.tremblay@example.com"))
                .thenReturn(Optional.of(professor));

        assertThatThrownBy(() -> userService.registerProfessor(dto))
                .isInstanceOf(EmailAlreadyUsedException.class);

        verify(professorRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void updateProfessor_shouldUpdateFields() {
        dto.setDiscipline("Computer Science");
        dto.setPassword(null);
        // email 不变 → 不需要 stub findUserAppByEmail

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(professorRepository.save(professor)).thenReturn(professor);
        when(professorMapper.toDto(professor)).thenReturn(dto);

        ProfessorDTO result = userService.updateProfessor(1L, dto);

        assertThat(result).isNotNull();
        assertThat(professor.getFirstName()).isEqualTo("Jean");
        assertThat(professor.getLastName()).isEqualTo("Tremblay");
        assertThat(professor.getDiscipline()).isEqualTo("Computer Science");
        verify(professorRepository).save(professor);
    }

    @Test
    void updateProfessor_shouldKeepOldPasswordWhenBlank() {
        dto.setPassword("");
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(professorRepository.save(any())).thenReturn(professor);
        when(professorMapper.toDto(any())).thenReturn(dto);

        userService.updateProfessor(1L, dto);

        verify(passwordEncoder, never()).encode(anyString());
        assertThat(professor.getPassword()).isEqualTo("encodedPassword");
    }

    @Test
    void updateProfessor_shouldEncodeNewPassword() {
        dto.setPassword("newSecret");
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(passwordEncoder.encode("newSecret")).thenReturn("newEncoded");
        when(professorRepository.save(any())).thenReturn(professor);
        when(professorMapper.toDto(any())).thenReturn(dto);

        userService.updateProfessor(1L, dto);

        verify(passwordEncoder).encode("newSecret");
        assertThat(professor.getPassword()).isEqualTo("newEncoded");
    }

    @Test
    void updateProfessor_shouldChangeEmailWhenNotUsed() {
        dto.setEmail("new.email@example.com");

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(userAppRepository.findUserAppByEmail("new.email@example.com"))
                .thenReturn(Optional.empty());
        when(professorRepository.save(any())).thenReturn(professor);
        when(professorMapper.toDto(any())).thenReturn(dto);

        userService.updateProfessor(1L, dto);

        assertThat(professor.getEmail()).isEqualTo("new.email@example.com");
        verify(userAppRepository).findUserAppByEmail("new.email@example.com");
    }

    @Test
    void updateProfessor_shouldThrowIfNotFound() {
        when(professorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateProfessor(99L, dto))
                .isInstanceOf(ProfessorNotFoundException.class);
    }

    @Test
    void updateProfessor_shouldThrowIfNewEmailAlreadyUsed() {
        dto.setEmail("other@example.com");
        Professor other = new Professor("Other", "Prof",
                new Credentials("other@example.com", "pw", Role.PROFESSOR), "Math");

        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(userAppRepository.findUserAppByEmail("other@example.com"))
                .thenReturn(Optional.of(other));

        assertThatThrownBy(() -> userService.updateProfessor(1L, dto))
                .isInstanceOf(EmailAlreadyUsedException.class);

        verify(professorRepository, never()).save(any());
    }

    @Test
    void getProfessorEmailById_shouldReturnEmail() {
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        String email = userService.getProfessorEmailById(1L);

        assertThat(email).isEqualTo("jean.tremblay@example.com");
    }

    @Test
    void getProfessorEmailById_shouldThrowIfNotFound() {
        when(professorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getProfessorEmailById(99L))
                .isInstanceOf(ProfessorNotFoundException.class);
    }

    @Test
    void getProfessorEmailByName_shouldReturnFirstMatch() {
        when(professorRepository.findByFullName("Jean", "Tremblay"))
                .thenReturn(List.of(professor));

        String email = userService.getProfessorEmailByName("Jean", "Tremblay");

        assertThat(email).isEqualTo("jean.tremblay@example.com");
    }

    @Test
    void getProfessorEmailByName_shouldThrowIfNotFound() {
        when(professorRepository.findByFullName("Unknown", "Person"))
                .thenReturn(List.of());

        assertThatThrownBy(() ->
                userService.getProfessorEmailByName("Unknown", "Person"))
                .isInstanceOf(ProfessorNotFoundException.class);
    }
}