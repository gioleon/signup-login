package com.signuploginbackend.service;

import com.signuploginbackend.model.Rol;
import com.signuploginbackend.repository.RolRepository;
import org.springframework.stereotype.Service;


@Service
public class RolServiceImpl {
    private RolRepository repository;

    public RolServiceImpl(RolRepository repository) {
        this.repository = repository;
    }

    public Iterable<Rol> findRoles(){
        return this.repository.findAll();
    }

    public void saveAll(Iterable<Rol> roles){
        this.repository.saveAll(roles);
    }

}
