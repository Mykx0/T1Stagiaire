package com.lacouf.rsbjwt.Mapper;

import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity(ProfessorDTO dto, Credentials credentials) {
        if (dto == null) {
            return null;
        }
        return new Professor(
                dto.getFirstName(),
                dto.getLastName(),
                credentials,
                dto.getDepartment(),
                dto.getSpecialization()
        );
    }

    public ProfessorDTO toDto(Professor professor) {
        return new ProfessorDTO(
                professor.getId(),
                professor.getFirstName(),
                professor.getLastName(),
                professor.getEmail(),
                professor.getDepartment(),
                professor.getSpecialization()
        );
    }
}