package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import com.hotel.Hotel_Reservation_Management.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerDTO dto) {
        Customer c = new Customer();

        c.setCustomerId(dto.getCustomerId());
        c.setUsername(dto.getUsername());
        c.setPassword(dto.getPassword());
        c.setFullName(dto.getFullName());
        c.setEmail(dto.getEmail());
        c.setPhoneNumber(dto.getPhoneNumber());
        c.setRole(dto.getRole());
        c.setStatus(dto.getStatus());

        return c;
    }

    public static CustomerDTO toDTO(Customer c) {
        CustomerDTO dto = new CustomerDTO();

        dto.setCustomerId(c.getCustomerId());
        dto.setUsername(c.getUsername());
        dto.setPassword(c.getPassword());
        dto.setFullName(c.getFullName());
        dto.setEmail(c.getEmail());
        dto.setPhoneNumber(c.getPhoneNumber());
        dto.setRole(c.getRole());
        dto.setStatus(c.getStatus());

        return dto;
    }
}