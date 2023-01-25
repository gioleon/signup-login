package com.signuploginbackend.rest;

import com.signuploginbackend.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("http://localhost:4200/")
public class LogInController {

    @PostMapping
    public void login(@RequestBody UserDTO userDTO){

    }

}
