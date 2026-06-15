package com.hotel.Hotel_Reservation_Management.serviceImpl;


import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.Hotel_Reservation_Management.dto.CustomerDTO;
import com.hotel.Hotel_Reservation_Management.entity.Customer;
import com.hotel.Hotel_Reservation_Management.mapper.CustomerMapper;
import com.hotel.Hotel_Reservation_Management.repository.CustomerRepository;
import com.hotel.Hotel_Reservation_Management.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private final CustomerRepository customerRepository;
	
	@Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {

        Customer customer = CustomerMapper.toEntity(dto);
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));

        return CustomerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return CustomerMapper.toDTO(
                customerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Customer not found"))
        );
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setFullName(dto.getFullName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setIdProof(dto.getIdProof());
        customer.setUsername(dto.getUsername());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return CustomerMapper.toDTO(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}