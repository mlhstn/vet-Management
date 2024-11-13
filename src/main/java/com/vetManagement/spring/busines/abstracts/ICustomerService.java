package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {

    Customer save(Customer customer);
    Customer get(Long id);
    boolean existsByCustomerName(String name);
    Page<Customer> cursor(int page, int pageSize);
}
