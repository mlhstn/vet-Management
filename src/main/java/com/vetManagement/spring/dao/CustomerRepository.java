package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
