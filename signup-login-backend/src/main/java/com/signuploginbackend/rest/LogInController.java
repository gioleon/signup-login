package com.signuploginbackend.rest;

import com.signuploginbackend.DTO.UserDTO;
import com.signuploginbackend.security.config.CustomUserDetail;
import com.signuploginbackend.security.config.CustomUserDetailService;
import com.signuploginbackend.security.jwt.JwtTokenService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/login")
public class LogInController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private CustomUserDetailService customUserDetailService;


    @PostMapping
    public ResponseEntity<HttpStatus> login(@RequestBody UserDTO userDTO, HttpServletResponse response) throws Exception {
        Authentication authentication;

        try {
            authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(), userDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            throw new Exception("BAD CREDENTIALS");
        }
        UserDetails userDetail = customUserDetailService
                .loadUserByUsername(userDTO.getEmail());

        response.addHeader("token", this.jwtTokenService.generateToken(userDetail));
        response.getWriter().flush();

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
