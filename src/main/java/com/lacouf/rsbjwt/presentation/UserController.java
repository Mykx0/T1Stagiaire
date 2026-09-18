package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.security.exception.EmailAlreadyUsedException;
import com.lacouf.rsbjwt.service.ProfService;
import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.ErrorResponse;
import com.lacouf.rsbjwt.service.dto.ProfessorDTO;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ProfService profService;

    public UserController(UserService userService, ProfService profService) {
        this.userService = userService;
        this.profService = profService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/user/checkEmail")
    public ResponseEntity<Boolean> isEmailUsed(@Email @RequestParam String email) {
        boolean isUsed = userService.isEmailUsed(email);
        return ResponseEntity.ok(isUsed);
    }

    @PostMapping("/register/student")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserDTO> createStudent(@Valid @RequestBody SignupDTO info) {
        var dto = userService.createStudent(
                info.firstName(),
                info.lastName(),
                info.email(),
                info.password(),
                info.discipline()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/register/prof")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> registerProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
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
}