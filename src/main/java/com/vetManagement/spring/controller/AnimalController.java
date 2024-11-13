package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.dto.request.Animal.AnimalSaveRequest;
import com.vetManagement.spring.dto.request.Animal.AnimalUpdateRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
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

        } catch (recordAlreadyExistException e) {

            Animal existingAnimal = iAnimalService.get(e.getId());

            AnimalResponse existingAnimalResponse = this.modelMapper.map(existingAnimal, AnimalResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingAnimalResponse);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {

        Animal animal = this.iAnimalService.get(id);
        AnimalResponse animalResponse = this.modelMapper.map(animal,AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.iAnimalService.cursor(page,pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.map(animal,AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }
}


