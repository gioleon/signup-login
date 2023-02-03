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
        if (header == null || !header.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        String username = this.jwtTokenService.validateTokenAndGetUsername(token);
        if (username==null){
            filterChain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authenticationToken =
                this.jwtTokenService.getAuthentication(username);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        filterChain.doFilter(request, response);
    }
}
