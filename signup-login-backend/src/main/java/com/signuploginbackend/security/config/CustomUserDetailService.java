package com.signuploginbackend.security.config;

import com.signuploginbackend.model.Rol;
import com.signuploginbackend.model.User;
import com.signuploginbackend.service.UserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private UserServiceImpl repository;

    CustomUserDetailService(UserServiceImpl repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = this.repository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format(
                            "USER_NOT_FOUND", email
                    ));
        }

        Collection<Rol> roles = user.get().getRoles();
        return new CustomUserDetail(user.get(), mapRoles(roles));
    }

    public Collection<? extends GrantedAuthority> mapRoles(Collection<Rol> roles) {
        return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
    }
}
