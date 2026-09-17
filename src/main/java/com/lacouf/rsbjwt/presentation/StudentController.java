package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.service.StudentService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/register/student")
    @CrossOrigin( origins = "http://localhost:3000")
    public ResponseEntity<UserDTO> createStudent(@Valid @RequestBody SignupDTO info) {
        var dto = studentService.createStudent(
                info.firstName(),
                info.lastName(),
                info.email(),
                info.password(),
                info.discipline()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
