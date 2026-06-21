package com.hotel.Hotel_Reservation_Management.service;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    public Optional<CustomerDTO> findByUsername(String username);
}