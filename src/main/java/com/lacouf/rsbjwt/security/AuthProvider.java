package com.lacouf.rsbjwt.security;

import com.lacouf.rsbjwt.model.User;
import com.lacouf.rsbjwt.repository.UserRepository;
import com.lacouf.rsbjwt.security.exception.AuthenticationException;
import com.lacouf.rsbjwt.security.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider implements AuthenticationProvider{
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public AuthProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		User user = loadUserByEmail(authentication.getPrincipal().toString());
		validateAuthentication(authentication, user);
		return new UsernamePasswordAuthenticationToken(
			user.getEmail(),
			user.getClass(),
			user.getAuthorities()
		);
	}

	@Override
	public boolean supports(Class<?> authentication){
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	private User loadUserByEmail(String email) throws UsernameNotFoundException{
		return userRepository.findUserAppByEmail(email)
			.orElseThrow(UserNotFoundException::new);
	}

	private void validateAuthentication(Authentication authentication, User user){
		if(!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()))
			throw new AuthenticationException(HttpStatus.FORBIDDEN, "Incorrect username or password");
	}
}
