package com.in28minutes.springboot.web.configuration;

import com.in28minutes.springboot.web.entity.AuthGroup;
import com.in28minutes.springboot.web.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserPrincipal implements UserDetails {
    private final User user;

    public CustomUserPrincipal(User user) {
        this.user = user;
    }

    /*
    * ROLE_ prefix is being add in GrantedAuthoritiesMapper bean
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AuthGroup> authGroups = user.getAuthGroups();
        if (authGroups == null) {
            return Collections.emptySet();
        }

        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGroups.forEach(group -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /*
    * Ideally when we build this out for production,
    * we would have the following values in our database
    * we just return true on all of those
    */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}