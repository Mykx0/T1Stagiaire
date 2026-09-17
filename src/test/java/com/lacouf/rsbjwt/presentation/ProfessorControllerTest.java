package com.lacouf.rsbjwt.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lacouf.rsbjwt.controller.ProfessorController;
import com.lacouf.rsbjwt.model.auth.Role;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.service.ProfService;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfessorController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProfessorControllerTest {

    @Autowired private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private ProfService profService;


    @Test
    void register_shouldReturnCreated() throws Exception {
        ProfessorDTO request = new ProfessorDTO(
                null,
                "Jean",
                "Tremblay",
                "jean.tremblay@example.com",
                "Software Engineering",
                Role.PROFESSOR,
                "password123"
        );

        ProfessorDTO response = new ProfessorDTO(
                1L,
                "Jean",
                "Tremblay",
                "jean.tremblay@example.com",
                "Software Engineering",
                Role.PROFESSOR,
                null
        );

        when(profService.registerProfessor(any(ProfessorDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/register/prof")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Jean"))
                .andExpect(jsonPath("$.lastName").value("Tremblay"))
                .andExpect(jsonPath("$.email").value("jean.tremblay@example.com"))
                .andExpect(jsonPath("$.discipline").value("Software Engineering"))
                .andExpect(jsonPath("$.role").value("PROFESSOR"))
                .andExpect(jsonPath("$.password").doesNotExist());

        verify(profService).registerProfessor(any(ProfessorDTO.class));
    }

    @Test
    void register_shouldReturnConflictWhenEmailUsed() throws Exception {
        ProfessorDTO request = new ProfessorDTO(
                null,
                "Jean",
                "Tremblay",
                "jean.tremblay@example.com",
                "Software Engineering",
                Role.PROFESSOR,
                "password123"
        );

        when(profService.registerProfessor(any(ProfessorDTO.class)))
                .thenThrow(new EmailAlreadyUsedException("jean.tremblay@example.com"));

        mockMvc.perform(post("/api/register/prof")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Email already used"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void register_shouldReturnBadRequestOnIllegalArgument() throws Exception {
        ProfessorDTO request = new ProfessorDTO(
                null,
                "Jean",
                "Tremblay",
                "jean.tremblay@example.com",
                "Software Engineering",
                Role.PROFESSOR,
                "password123"
        );

        when(profService.registerProfessor(any(ProfessorDTO.class)))
                .thenThrow(new IllegalArgumentException("bad input"));

        mockMvc.perform(post("/api/register/prof")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid input"))
                .andExpect(jsonPath("$.message").value("bad input"));
    }

    @Test
    void register_shouldReturnInternalErrorOnUnexpectedException() throws Exception {
        ProfessorDTO request = new ProfessorDTO(
                null,
                "Jean",
                "Tremblay",
                "jean.tremblay@example.com",
                "Software Engineering",
                Role.PROFESSOR,
                "password123"
        );

        when(profService.registerProfessor(any(ProfessorDTO.class)))
                .thenThrow(new RuntimeException("boom"));

        mockMvc.perform(post("/api/register/prof")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal error"))
                .andExpect(jsonPath("$.message").value("boom"));
    }


}