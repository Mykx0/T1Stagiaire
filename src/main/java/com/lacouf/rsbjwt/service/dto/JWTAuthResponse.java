package com.lacouf.rsbjwt.service.dto;

public class JWTAuthResponse {
    private final String tokenType = "BEARER";
    private String accessToken;

    public JWTAuthResponse() {}

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}