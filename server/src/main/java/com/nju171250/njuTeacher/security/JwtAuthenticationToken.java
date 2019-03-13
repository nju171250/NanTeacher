package com.nju171250.njuTeacher.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthenticationToken {
    private String header;
    private String payload;
    private String token;
    private String signature;

    public JwtAuthenticationToken(DecodedJWT decodedJWT){
        this.header = decodedJWT.getHeader();
        this.payload = decodedJWT.getPayload();
        this.token = decodedJWT.getToken();
        this.signature = decodedJWT.getSignature();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
