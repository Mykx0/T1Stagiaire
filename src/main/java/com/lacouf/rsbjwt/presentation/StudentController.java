package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.presentation.dto.SignupDTO;
import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/student")
public class StudentController {
    private final UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @CrossOrigin( origins = "http://localhost:3000")
    public ResponseEntity<UserDTO> CreateStudent(@RequestBody SignupDTO info) {
        var dto = userService.CreateStudent(
                info.firstName(),
                info.lastName(),
                info.email(),
                info.password()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> GetStudent(@PathVariable Long id) {
        return ResponseEntity.ok(userService.GetUser(id));
    }
}
