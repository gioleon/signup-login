package com.signuploginbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtTokenService {

    // Algorithm to encrypt
    private final int JWT_TOKEN_VALIDITY = 86400;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(UserDetails UserDetails) {
        return JWT.create()
                .withSubject(UserDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.JWT_TOKEN_VALIDITY))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException verificationException) {
            log.warn("Token invalid: {}", verificationException.getMessage());
            return null;
        }
    }


}
