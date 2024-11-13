package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.ICustomerService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

    private  final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        if (customerRepository.findByCustomerName(customer.getCustomerName()) != null){
            throw new recordAlreadyExistException(customer.getId());
        }
        customer.setId(null);
        return customerRepository.save(customer);
    }

    @Override
    public boolean existsByCustomerName(String customerName) {
        return customerRepository.existsByCustomerName(customerName);
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer get(Long id) {

        return this.customerRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
}
