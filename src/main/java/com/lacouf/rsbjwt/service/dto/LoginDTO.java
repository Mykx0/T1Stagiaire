package com.lacouf.rsbjwt.service.dto;

public class LoginDTO{
	private String email;
	private String password;

	public LoginDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public LoginDTO() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
