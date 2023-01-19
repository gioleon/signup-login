package com.signuploginbackend.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private LocalDate dayOfBirth;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Rol> roles;

    public User(String name, String lastName, LocalDate dateOfBirth, String email, String password, Collection<Rol> roles) {
        this.name = name;
        this.lastName = lastName;
        this.dayOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


}
