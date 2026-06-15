package com.hotel.Hotel_Reservation_Management.security;

import com.hotel.Hotel_Reservation_Management.entity.Admin;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.repository.AdminRepository;
import com.hotel.Hotel_Reservation_Management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> admin = adminRepository.findByUsername(username);

        if (admin.isPresent()) {
            return new CustomUserDetails(admin.get());
        }

        Optional<Customer> customer = customerRepository.findByUsername(username);

        if (customer.isPresent()) {
            return new CustomUserDetails(customer.get());
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}