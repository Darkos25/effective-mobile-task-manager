package com.example.EffectiveMobile.DTO.auth;

import lombok.Getter;

public class JwtResponse {

    @Getter
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }


}
