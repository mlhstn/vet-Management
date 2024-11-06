package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.ICustomerService;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

    CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        if (customerRepository.findByName(customer.getName()) != null){
            throw new recordAlreadyExistException(customerRepository.findByName(customer.getName()).getId());
        }
        customer.setId(null);
        return customerRepository.save(customer);
    }
}
