package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.Mapper.ProfessorMapper;
import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import com.lacouf.rsbjwt.repository.ProfessorRepository;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfessorMapper professorMapper;

    public ProfessorService(
            ProfessorRepository professorRepository,
            UserAppRepository userAppRepository,
            PasswordEncoder passwordEncoder,
            ProfessorMapper professorMapper
    ) {
        this.professorRepository = professorRepository;
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO register(ProfessorDTO dto) {
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
        Professor savedProfessor = professorRepository.save(professor);

        return professorMapper.toDto(savedProfessor);
    }
}