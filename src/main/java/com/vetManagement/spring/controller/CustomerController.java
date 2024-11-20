package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.ICustomerService;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dto.request.Customer.CustomerSaveRequest;
import com.vetManagement.spring.dto.request.Customer.CustomerUpdateRequest;
import com.vetManagement.spring.dto.request.Doctor.DoctorSaveRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Customer.CustomerResponse;
import com.vetManagement.spring.dto.response.Doctor.DoctorResponse;
import com.vetManagement.spring.entity.Customer;
import com.vetManagement.spring.entity.Doctor;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService iCustomerService;
    private final ImodelMapperService modelMapper;

    public CustomerController(ICustomerService iCustomerService, ImodelMapperService modelMapper) {
        this.iCustomerService = iCustomerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> saveCustomer(@RequestBody @Validated CustomerSaveRequest customerSaveRequest){

        Customer customer = modelMapper.forRequest().map(customerSaveRequest, Customer.class);

        try {
            Customer savedCustomer = iCustomerService.save(customer);

            CustomerResponse customerResponse = this.modelMapper.forResponse().map(savedCustomer, CustomerResponse.class);

            return ResultHelper.created(customerResponse);

        }catch (recordAlreadyExistException e){
            Customer existingCustomer = iCustomerService.get(e.getId());

            CustomerResponse existingCustomerResponse  = this.modelMapper.forResponse().map(existingCustomer, CustomerResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingCustomerResponse);
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") Long id) {

        Customer customer = this.iCustomerService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer,CustomerResponse.class);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = this.iCustomerService.cursor(page,pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer,CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest){

        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.iCustomerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){

        this.iCustomerService.delete(id);
        return ResultHelper.ok();

    }

    @GetMapping("/sorted")
    public ResponseEntity<List<CustomerResponse>> getAllCustomersSorted() {
        List<CustomerResponse> sortedCustomers = iCustomerService.getAllCustomersSorted();
        return ResponseEntity.ok(sortedCustomers);
    }


}
