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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfService {


    private final UserAppRepository userAppRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfessorMapper professorMapper;

    public ProfService(
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider,
            UserAppRepository userAppRepository,
            ProfessorRepository professorRepository,
            PasswordEncoder passwordEncoder,
            ProfessorMapper professorMapper
    ) {
        this.userAppRepository = userAppRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO registerProfessor(ProfessorDTO dto) {
        String email = dto.getEmail().trim().toLowerCase();

        if (userAppRepository.findUserAppByEmail(email).isPresent()) {
            throw new EmailAlreadyUsedException(email);
        }

        Credentials credentials = new Credentials(
                email,
                passwordEncoder.encode(dto.getPassword()),
                Role.PROFESSOR
        );

        Professor professor = professorMapper.toEntity(dto, credentials);
        return professorMapper.toDto(professorRepository.save(professor));
    }


    public ProfessorDTO updateProfessor(Long id, ProfessorDTO dto) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException(id));

        String newEmail = dto.getEmail().trim().toLowerCase();
        String currentEmail = professor.getEmail();

        if (!newEmail.equalsIgnoreCase(currentEmail)
                && userAppRepository.findUserAppByEmail(newEmail).isPresent()) {
            throw new EmailAlreadyUsedException(newEmail);
        }

        professor.setFirstName(dto.getFirstName());
        professor.setLastName(dto.getLastName());
        professor.setDiscipline(dto.getDiscipline());

        String newPassword = dto.getPassword();
        String encodedPassword = (newPassword == null || newPassword.isBlank())
                ? professor.getPassword()
                : passwordEncoder.encode(newPassword);

        professor.setCredentials(new Credentials(newEmail, encodedPassword, Role.PROFESSOR));

        return professorMapper.toDto(professorRepository.save(professor));
    }


    public String getProfessorEmailById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException(id));
        return professor.getEmail();
    }

    public String getProfessorEmailByName(String firstName, String lastName) {
        List<Professor> professors = professorRepository.findByFullName(firstName, lastName);
        if (professors.isEmpty()) {
            throw new ProfessorNotFoundException(
                    "No professor found for " + firstName + " " + lastName);
        }
        return professors.get(0).getEmail();
    }
}