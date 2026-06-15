package com.hotel.Hotel_Reservation_Management.security;

import com.hotel.Hotel_Reservation_Management.entity.Admin;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String role;
    private boolean enabled;

    // ADMIN constructor
    public CustomUserDetails(Admin admin) {
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.role = admin.getRole().name();
        this.enabled = admin.getStatus().name().equals("ACTIVE");
    }

    // CUSTOMER constructor
    public CustomUserDetails(Customer customer) {
        this.username = customer.getUsername();
        this.password = customer.getPassword();
        this.role = customer.getRole().name();
        this.enabled = customer.getStatus().name().equals("ACTIVE");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + role)
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}