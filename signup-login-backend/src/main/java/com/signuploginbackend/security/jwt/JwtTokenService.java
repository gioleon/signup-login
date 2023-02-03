package com.signuploginbackend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.signuploginbackend.model.Rol;
import com.signuploginbackend.security.config.CustomUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtTokenService {

    // Algorithm to encrypt
    private final int JWT_TOKEN_VALIDITY = 86400;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;
    private final String secret;
    private final CustomUserDetailService customUserDetailService;

    public JwtTokenService(@Value("${jwt.secret}") String secret, CustomUserDetailService customUserDetailService) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
        this.secret = secret;
        this.customUserDetailService = customUserDetailService;
    }

    public String generateToken(UserDetails userDetails) {

        // Convert collection of type GrantedAuthorities to String
        List<String> authorities = userDetails.getAuthorities()
                .stream().map(rol -> rol.toString()).collect(Collectors.toList());

        // Create a hashMap and pass those authorities to it
        Map<String, List<String>> extra = new HashMap<>();
        extra.put("Authorities", authorities);

        // return token
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("extra", extra)
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

    public UsernamePasswordAuthenticationToken getAuthentication(String username) {

        // load a username if exists with the given email
        UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, userDetails.getAuthorities());
    }


}
