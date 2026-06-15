package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDto;
import com.hotel.Hotel_Reservation_Management.entity.Customer;

public class CustomerMapper {

    public static CustomerDto toDto(Customer customer) {
        if (customer == null) return null;

        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setFullName(customer.getFullName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setIdProof(customer.getIdProof());
        dto.setUsername(customer.getUsername());
        dto.setPassword(customer.getPassword());

        return dto;
    }

    public static Customer toEntity(CustomerDto dto) {
        if (dto == null) return null;

        Customer customer = new Customer();
        customer.setCustomerId(dto.getCustomerId());
        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setIdProof(dto.getIdProof());
        customer.setUsername(dto.getUsername());
        customer.setPassword(dto.getPassword());

        return customer;
    }
}