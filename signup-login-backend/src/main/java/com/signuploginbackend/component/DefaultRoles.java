package com.signuploginbackend.component;

import com.signuploginbackend.model.Rol;
import com.signuploginbackend.service.RolServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

@Component
public class DefaultRoles {

    private RolServiceImpl repository;

    public DefaultRoles(RolServiceImpl repository) {
        this.repository = repository;
    }


    @PostConstruct
    public void saveRoles() {

        List<Rol> roles = (List<Rol>) this.repository.findRoles();

        if (roles.isEmpty()) {
            this.repository.saveAll(
                    Arrays.asList(
                            new Rol(1, "ROLE_USER"),
                            new Rol("ROLE_ADMIN")
                    )
            );
        }


    }


}
