package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.dto.request.Animal.AnimalSaveRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final IAnimalService iAnimalService;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public AnimalController(IAnimalService iAnimalService, ModelMapper modelMapper, CustomerRepository customerRepository) {
        this.iAnimalService = iAnimalService;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> saveAnimal(@RequestBody @Validated AnimalSaveRequest animalSaveRequest) {

        Animal animal = modelMapper.map(animalSaveRequest, Animal.class);

        try {
            Animal savedAnimal = iAnimalService.save(animal);

            AnimalResponse animalResponse = this.modelMapper.map(savedAnimal, AnimalResponse.class);

            return ResultHelper.created(animalResponse);

        }catch(recordAlreadyExistException e){

            Animal existingAnimal = iAnimalService.get(e.getId());

            AnimalResponse existingAnimalResponse = this.modelMapper.map(existingAnimal, AnimalResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingAnimalResponse);
        }
        }



}


