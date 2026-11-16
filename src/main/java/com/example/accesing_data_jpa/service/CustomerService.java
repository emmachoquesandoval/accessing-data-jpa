package com.example.accesing_data_jpa.service;

import com.example.accesing_data_jpa.dto.CustomerDto;
import com.example.accesing_data_jpa.model.Customer;
import com.example.accesing_data_jpa.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Customer c = customerRepository.findById(id);
        CustomerDto cdto = new CustomerDto(c.getFirstName(), c.getLastName());
        return cdto;
    }

    public List<CustomerDto> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName)
                .stream().map(customer -> new CustomerDto(customer.getFirstName(), customer.getLastName()))
                .toList();
    }
}
