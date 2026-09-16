package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/student")
    @CrossOrigin( origins = "http://localhost:3000")
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
