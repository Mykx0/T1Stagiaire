package com.lacouf.rsbjwt.model.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Embeddable
public final class Credentials implements UserDetails {

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	public Credentials(String email, @Nullable String encode, String professor) {
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Credentials(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Credentials() {
	}

	public String getEmail() {
		return email;
	}
	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Credentials{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				'}';
	}
}
