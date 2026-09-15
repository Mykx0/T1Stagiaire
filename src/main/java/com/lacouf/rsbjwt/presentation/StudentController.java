package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import com.lacouf.rsbjwt.service.exceptions.BadInputException;
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
    public ResponseEntity<UserDTO> createStudent(@RequestBody SignupDTO info) {

        try {
            var dto = userService.createStudent(
                    info.firstName(),
                    info.lastName(),
                    info.email(),
                    info.password(),
                    info.discipline()
            );
        } catch (BadInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> GetStudent(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
