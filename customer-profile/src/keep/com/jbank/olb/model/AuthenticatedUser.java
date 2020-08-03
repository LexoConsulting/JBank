package com.jbank.olb.customerProfile.security.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Holds the info for a authenticated user (Principal)
 */
public class AuthenticatedUser implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final Long id;
    private final String username;
    
    private final String token;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getPassword() {
    	// Not keep the password in memory, return null here
        return null;
    }

    public AuthenticatedUser(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        
        this.token = token;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
