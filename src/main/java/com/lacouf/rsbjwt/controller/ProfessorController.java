package com.lacouf.rsbjwt.controller;

import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.security.exception.ProfessorNotFoundException;
import com.lacouf.rsbjwt.service.ProfService;
import com.lacouf.rsbjwt.service.dto.ErrorResponse;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
// Notre Front-End est sur le 3000. Il va falloir que tu changes l'origine
public class ProfessorController {

    private final ProfService profService;

    public ProfessorController(ProfService userService) {
        this.profService = userService;
    }


    @PostMapping("/register/prof")
    // On s'est mis d'accord sur le fait que nos path de register seraient /api/register/[?]
    // Dans ton cas, c'est /api/professor/register.
    public ResponseEntity<?> register(@RequestBody ProfessorDTO professorDTO) throws EmailAlreadyUsedException{
        try {
            ProfessorDTO response = profService.registerProfessor(professorDTO);
                 return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ErrorResponse.emailAlreadyUsed(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.invalidInput(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.internalError(e.getMessage()));
        }

    }

    // GET /api/prof/email?firstName= &lastName=
    @GetMapping("/prof/email")
    public ResponseEntity<?> getEmailByName(
            @RequestParam String firstName,
            @RequestParam String lastName
    ) {
        try {
            return ResponseEntity.ok(profService.getProfessorEmailByName(firstName, lastName));
        } catch (ProfessorNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.professorNotFound(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.internalError(e.getMessage()));
        }
    }

    // PUT /api/prof/updateProfil/{profId}
    @PutMapping("/prof/updateProfil/{profId}")
         public ResponseEntity<?> updateProfProfil(
                  @PathVariable Long profId,
                  @RequestBody ProfessorDTO professorDTO
          ) {
             try {
                 ProfessorDTO response = profService.updateProfessor(profId, professorDTO);
                  return ResponseEntity.ok(response);
              } catch (ProfessorNotFoundException e) {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(ErrorResponse.professorNotFound(e.getMessage()));
              } catch (EmailAlreadyUsedException e) {
                 return ResponseEntity.status(HttpStatus.CONFLICT)
                                         .body(ErrorResponse.emailAlreadyUsed(e.getMessage()));
              } catch (IllegalArgumentException e) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(ErrorResponse.invalidInput(e.getMessage()));
             } catch (Exception e) {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                        .body(ErrorResponse.internalError(e.getMessage()));
              }
          }
}