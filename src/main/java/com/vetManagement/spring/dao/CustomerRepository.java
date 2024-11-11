package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByCustomerName(String customerName);
    boolean existsByCustomerName(String customerName);
}
