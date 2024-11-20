package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.ICustomerService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Customer.CustomerResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private  final CustomerRepository customerRepository;


    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        ;
    }

    @Override
    public Customer save(Customer customer) {
        if (customerRepository.findByCustomerName(customer.getCustomerName()) != null){

            throw new recordAlreadyExistException(customerRepository.findByCustomerName(customer.getCustomerName()).getId());
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

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepository.delete(customer);
        return true;
    }

    @Override
    public List<CustomerResponse> getAllCustomersSorted() {
        // Müşterileri customerName'e göre sıralıyoruz
        List<Customer> customers = customerRepository.findAllByOrderByCustomerNameAsc();

        // Sıralanmış müşteri listesini CustomerResponse formatında döndürüyoruz
        return customers.stream()
                .map(customer -> new CustomerResponse(
                        customer.getId(),
                        customer.getCustomerName(),
                        customer.getPhone(),
                        customer.getMail(),
                        customer.getAddress(),
                        customer.getCity(),
                        null // Hayvan listesi olmadığı için null
                ))
                .collect(Collectors.toList());
    }

}
