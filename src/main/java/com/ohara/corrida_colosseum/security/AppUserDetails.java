package com.ohara.corrida_colosseum.security;


import com.ohara.corrida_colosseum.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {
    protected User user;

    public AppUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(
                "ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    public String getFirstName() {
        return user.getFirstName();
    }

    public String getPhone() {
        return user.getPhone();
    }

    public Long getId() {
        return user.getId();
    }

    public User getUser() {
        return this.user;
    }
}
