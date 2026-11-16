package com.example.accesing_data_jpa.service;

import com.example.accesing_data_jpa.dto.CustomerDto;
import com.example.accesing_data_jpa.model.Customer;
import com.example.accesing_data_jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> lc = customerRepository.findAll();
        List<CustomerDto> cdto = lc.stream().map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName())).toList();
        return cdto;
    }

    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id));
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName)
                .stream().map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
                .toList();
    }
}
