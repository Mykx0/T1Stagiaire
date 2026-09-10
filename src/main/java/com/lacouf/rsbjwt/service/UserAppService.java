package com.lacouf.rsbjwt.service;

import com.lacouf.rsbjwt.model.*;
import com.lacouf.rsbjwt.repository.UserAppRepository;
import com.lacouf.rsbjwt.service.dto.*;
import com.lacouf.rsbjwt.security.JwtTokenProvider;
import com.lacouf.rsbjwt.security.exception.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserAppRepository userAppRepository;

    public UserAppService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserAppRepository userAppRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userAppRepository = userAppRepository;
    }
// La logique du code peut être utilisée pour l'authentification
//    public String authenticateUser(LoginDTO loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//        final String token = jwtTokenProvider.generateToken(authentication);
//        System.out.println("JWT Token " + token);
//        return token;
//    }

//    public UserDTO getMe(String token) {
//        token = token.startsWith("Bearer") ? token.substring(7) : token;
//        String email = jwtTokenProvider.getEmailFromJWT(token);
//        UserApp user = userAppRepository.findUserAppByEmail(email).orElseThrow(UserNotFoundException::new);
//        return switch(user.getRole()){
//            case EMPRUNTEUR -> getEmprunteurDto(user.getId());
//            case PREPOSE -> getPreposeDto(user.getId());
//            case GESTIONNAIRE -> getGestionnaireDto(user.getId());
//        };
//    }
}
