package com.capellax.spring_fundamentals.api.model;

/**
 * The response object sent from login requests
 */
public class LoginResponse {

    /** The JWT token to be used for authentication. */
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

}
