package com.lacouf.rsbjwt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lacouf.rsbjwt.model.Discipline;
import com.lacouf.rsbjwt.model.Professor;
import com.lacouf.rsbjwt.model.auth.Credentials;
import com.lacouf.rsbjwt.model.auth.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

public record ProfessorDTO(
        Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotNull Discipline discipline,
        Role role,

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @NotBlank @Size(min = 8)
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