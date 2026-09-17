package com.lacouf.rsbjwt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import org.springframework.stereotype.Component;

public record ProfessorDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String discipline,
        Role role,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        String password
) {
    @Component
    public record Mapper() {
        public Professor toEntity(ProfessorDTO dto, Credentials credentials) {
            return new Professor(
                    dto.firstName(),
                    dto.lastName(),
                    credentials,
                    dto.discipline()
            );
        }

        public ProfessorDTO toDto(Professor professor) {
            return new ProfessorDTO(
                    professor.getId(),
                    professor.getFirstName(),
                    professor.getLastName(),
                    professor.getEmail(),
                    professor.getDiscipline(),
                    Role.PROFESSOR,
                    null
            );
        }
    }
}