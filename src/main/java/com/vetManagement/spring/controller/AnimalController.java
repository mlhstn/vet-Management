package com.vetManagement.spring.controller;


import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dao.CustomerRepository;
import com.vetManagement.spring.dto.request.Animal.AnimalSaveRequest;
import com.vetManagement.spring.dto.request.Animal.AnimalUpdateRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.entity.Animal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private final IAnimalService iAnimalService;
    private final ImodelMapperService modelMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public AnimalController(IAnimalService iAnimalService, ImodelMapperService modelMapper,
                            CustomerRepository customerRepository) {
        this.iAnimalService = iAnimalService;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> saveAnimal(@RequestBody AnimalSaveRequest animalSaveRequest) {

        Animal animal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);

        try {
            // AnimalSaveRequest'i doğrudan kullanarak kaydetme işlemi yapalım
            Animal savedAnimal = iAnimalService.save(animal);

            // Kaydedilen hayvanı yanıt olarak döndürelim
            AnimalResponse animalResponse = this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class);

            return ResultHelper.created(animalResponse);

        } catch (recordAlreadyExistException e) {
            // Eğer hayvan zaten varsa, mevcut hayvan bilgisini döndürelim
            Animal existingAnimal = iAnimalService.get(e.getId());

            AnimalResponse existingAnimalResponse = this.modelMapper.forResponse().map(existingAnimal, AnimalResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingAnimalResponse);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {

        Animal animal = this.iAnimalService.get(id);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal,AnimalResponse.class);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.iAnimalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){

        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.iAnimalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {

        this.iAnimalService.delete(id);
        return ResultHelper.ok();

    }

    @GetMapping("/sorted")
    public ResponseEntity<List<AnimalResponse>> getAllAnimalsSorted() {
        List<AnimalResponse> sortedAnimals = iAnimalService.getAllAnimalsSorted();
        return ResponseEntity.ok(sortedAnimals);
    }

    @GetMapping("customers/{id}")
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable Long id){

        List<AnimalResponse> responses = iAnimalService.getAnimalsByCustomerId(id);
        return ResultHelper.ok(responses);
    }

}


