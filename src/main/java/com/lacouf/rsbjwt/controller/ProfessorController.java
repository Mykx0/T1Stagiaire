package com.lacouf.rsbjwt.controller;

import com.lacouf.rsbjwt.service.ProfessorService;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profRegister")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping("/profRegister")
    public ResponseEntity<ProfessorDTO> register(
            @RequestBody ProfessorDTO professorDTO
    ) {
        ProfessorDTO response = professorService.register(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/updateProfProfil/{profId}")
    public ResponseEntity<ProfessorDTO> updateProfProfil(
            @PathVariable Long profId,
            @RequestBody ProfessorDTO professorDTO
    ) {
        ProfessorDTO response = professorService.update(profId, professorDTO);
        return ResponseEntity.ok(response);
    }
}