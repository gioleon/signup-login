package com.signuploginbackend.security.jwt;

import com.signuploginbackend.security.config.CustomUserDetail;
import com.signuploginbackend.security.config.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtTokenService jwtTokenService;
    private CustomUserDetailService customUserDetailService;

    JwtRequestFilter(JwtTokenService jwtTokenService, CustomUserDetailService customUserDetailService) {
        this.jwtTokenService = jwtTokenService;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Get Token
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Check if the token is valid
        if (validToken(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Check there is a username into the token
        final String username = getUsernameFromToken(header);
        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Load a user into the spring security context if exists.
        final UserDetails userDetails = this.customUserDetailService.loadUserByUsername(username);
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        filterChain.doFilter(request, response);
    }

    public boolean validToken(String token) {
        if (token == null || !token.contains("Bearer")) {
            return false;
        }
        return true;
    }

    public String getUsernameFromToken(String token) {
        String encryptedUsername = token.substring(7);
        String username = this.jwtTokenService.validateTokenAndGetUsername(encryptedUsername);

        return username;
    }
}
