package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import com.lacouf.rsbjwt.repository.ProfessorRepository;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.security.exception.ProfessorNotFoundException;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ProfService {

    private final UserAppRepository userAppRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfessorDTO.Mapper professorMapper;

    public ProfService(
            UserAppRepository userAppRepository,
            ProfessorRepository professorRepository,
            PasswordEncoder passwordEncoder,
            ProfessorDTO.Mapper professorMapper
    ) {
        this.userAppRepository = userAppRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
        this.professorMapper = professorMapper;
    }

    public ProfessorDTO registerProfessor(ProfessorDTO dto) throws EmailAlreadyUsedException {
        String email = dto.email().trim().toLowerCase();

        if (userAppRepository.findUserAppByEmail(email).isPresent()) {
            throw new EmailAlreadyUsedException(email);
        }

        Credentials credentials = new Credentials(
                email,
                passwordEncoder.encode(dto.password()),
                Role.PROFESSOR
        );

        Professor professor = professorMapper.toEntity(dto, credentials);
        return professorMapper.toDto(professorRepository.save(professor));
    }




}