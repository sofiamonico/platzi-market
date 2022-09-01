package com.platzi.market.domain.dto;

public class AuthenticationReponse {

    private String jwt;

    public AuthenticationReponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
