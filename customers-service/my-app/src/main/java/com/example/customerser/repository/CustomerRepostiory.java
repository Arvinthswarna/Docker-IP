package com.example.customerser.repository;

import com.example.customerser.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepostiory extends JpaRepository<Customer, Long> {
    Customer findByCustId(Long id);

    
}
