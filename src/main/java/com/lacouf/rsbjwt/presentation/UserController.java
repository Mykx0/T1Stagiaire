package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.service.UserService;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}
