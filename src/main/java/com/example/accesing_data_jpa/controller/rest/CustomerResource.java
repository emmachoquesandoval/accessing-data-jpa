package com.example.accesing_data_jpa.controller.rest;

import com.example.accesing_data_jpa.dto.CustomerDto;
import com.example.accesing_data_jpa.model.Customer;
import com.example.accesing_data_jpa.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(CustomerResource.CUSTOMERS)
public class CustomerResource {
    public static final String CUSTOMERS = "/customers";

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(@RequestParam(name = "last_name", required = false)String lastName) {
        List<CustomerDto> lc;
        if(lastName == null) lc = customerService.getAllCustomers();
        else lc = customerService.getCustomersByLastName(lastName);
        return ResponseEntity.ok(lc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        CustomerDto c= customerService.getCustomerById(id);
        return ResponseEntity.ok(c);
    }
}
