package com.lacouf.rsbjwt.controller;

import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.security.exception.ProfessorNotFoundException;
import com.lacouf.rsbjwt.service.ProfService;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/professor")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfessorController {

    private final ProfService profService;

    public ProfessorController(ProfService userService) {
        this.profService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ProfessorDTO professorDTO) {
        try {
            ProfessorDTO response = profService.registerProfessor(professorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Email already used", "message", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid input", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal error", "message", e.getMessage()));
        }
    }

    @GetMapping("/{profId}/email")
    public ResponseEntity<?> getEmailById(@PathVariable Long profId) {
        try {
            return ResponseEntity.ok(profService.getProfessorEmailById(profId));
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Professor not found", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal error", "message", e.getMessage()));
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getEmailByName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        try {
            return ResponseEntity.ok(profService.getProfessorEmailByName(firstName, lastName));
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Professor not found", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal error", "message", e.getMessage()));
        }
    }

    // PUT /api/professor/updateProfil/{profId}
    @PutMapping("updateProfil/{profId}")
    public ResponseEntity<?> updateProfProfil(
            @PathVariable Long profId,
            @RequestBody ProfessorDTO professorDTO
    ) {
        try {
            ProfessorDTO response = profService.updateProfessor(profId, professorDTO);
            return ResponseEntity.ok(response);
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Professor not found", "message", e.getMessage()));
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Email already used", "message", e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Invalid input", "message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal error", "message", e.getMessage()));
        }
    }
}