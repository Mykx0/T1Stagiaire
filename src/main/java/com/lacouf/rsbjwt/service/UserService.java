package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.User;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.security.JwtTokenProvider;
import com.lacouf.rsbjwt.security.exception.UserNotFoundException;
import com.lacouf.rsbjwt.service.dto.LoginDTO;
import com.lacouf.rsbjwt.service.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo, AuthenticationManager authManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepo = userRepo;
        this.authManager = authManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public UserDTO getUser(long id) {
        return new UserDTO(userRepo.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public boolean isEmailUsed(String email) {
        return userRepo.findUserAppByEmail(email).isPresent();
    }

    public UserDTO getMe(String token) {
        token = token.startsWith("Bearer") ? token.substring(7) : token;
        String email = jwtTokenProvider.getEmailFromJWT(token);
        User user = userRepo.findUserAppByEmail(email).orElseThrow(UserNotFoundException::new);
        return new UserDTO(user);
    }

    public String authenticateUser(LoginDTO loginDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        final String token = jwtTokenProvider.generateToken(authentication);
        System.out.println("JWT Token " + token);
        return token;
    }
}
