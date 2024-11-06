package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;

public interface ICustomerService {

    Customer save(Customer customer);
}
