package com.example.customerser.service;

import com.example.customerser.entity.Customer;
import com.example.customerser.repository.CustomerRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepostiory customerRepostiory;

    public Customer saveCustomer(Customer customer) {
        return customerRepostiory.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepostiory.findByCustId(id);
    }

    public List<Customer>getAllCustomer(){
        return customerRepostiory.findAll();
    }
}



