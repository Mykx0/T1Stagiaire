package com.lacouf.rsbjwt.presentation.dto;

import com.lacouf.rsbjwt.model.Discipline;;

public record SignupDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        Discipline discipline) {
}
