package com.example.customerser.controller;

import com.example.customerser.entity.Customer;
import com.example.customerser.repository.CustomerRepostiory;
import com.example.customerser.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins="*")
public class CustomerController {

    @Autowired
    private CustomerRepostiory customerRepostiory;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/fetch")
    public List<Customer> getAllCustomer(){
        List<Customer> customer = new ArrayList<>();
        return customerService.getAllCustomer();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        Optional<Customer> customerData = customerRepostiory.findById(id);
        if(customerData.isPresent()){
            Customer _customer = customerData.get();
            _customer.setCustName(customer.getCustName());
            _customer.setCustEmail(customer.getCustEmail());
            _customer.setCustPhone(customer.getCustPhone());
            _customer.setCustCity(customer.getCustCity());
            _customer.setCustState(customer.getCustState());
            _customer.setCustCountry(customer.getCustCountry());

            return new ResponseEntity<>(customerRepostiory.save(_customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id){
        try{
            customerRepostiory.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


