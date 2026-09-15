package com.lacouf.rsbjwt.presentation.dto;

import com.lacouf.rsbjwt.model.Discipline;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank Discipline discipline) {
}
