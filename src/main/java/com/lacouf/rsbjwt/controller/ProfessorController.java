package com.lacouf.rsbjwt.controller;

import com.lacouf.rsbjwt.service.UserAppService;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfessorController {

    private final UserAppService userAppService;

    public ProfessorController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping("/register//professor")
    public ResponseEntity<ProfessorDTO> register(@RequestBody ProfessorDTO professorDTO) {
        ProfessorDTO response = userAppService.registerProfessor(professorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/professor/{profId}/email
    @GetMapping("/{profId}/email")
    public ResponseEntity<String> getEmailById(@PathVariable Long profId) {
        return ResponseEntity.ok(userAppService.getProfessorEmailById(profId));
    }

    // GET /api/professor/email
    @GetMapping("/email")
    public ResponseEntity<String> getEmailByName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        return ResponseEntity.ok(userAppService.getProfessorEmailByName(firstName, lastName));
    }

}