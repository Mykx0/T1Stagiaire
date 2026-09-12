package com.lacouf.rsbjwt.presentation;

import com.lacouf.rsbjwt.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/student")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public RequestBody CreateStudent() {
        return
    }
}
