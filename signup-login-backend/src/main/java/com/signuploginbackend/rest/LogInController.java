package com.signuploginbackend.rest;

import com.signuploginbackend.DTO.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/login")
public class LogInController {

    private AuthenticationManager authenticationManager;

    LogInController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    public String helloLogin(){
        return "hello Login";
    }

    @PostMapping
    public ResponseEntity<HttpStatus> login(@RequestBody UserDTO userDTO) throws Exception {
        Authentication authentication;

        try {
            authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(), userDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            throw new Exception("BAD CREDENTIALS");
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

}
