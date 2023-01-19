package com.signuploginbackend.component;

import com.signuploginbackend.model.Rol;
import com.signuploginbackend.service.RolServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.Arrays;

//@Component
public class DefaultRoles {

    private RolServiceImpl repository;

    public DefaultRoles(RolServiceImpl repository){
        this.repository = repository;
    }


    @PostConstruct
    public void saveRoles(){
        this.repository.saveAll(
                Arrays.asList(
                        new Rol(1, "ROLE_USER"),
                        new Rol("ROLE_ADMIN")
                )
        );
    }



}
