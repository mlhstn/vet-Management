package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.AnimalRepository;
import com.vetManagement.spring.entity.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    public AnimalManager(AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Animal save(Animal animal) {
        Animal existingAnimal = animalRepository.findByName(animal.getName());

        if (existingAnimal != null) {
            // Eğer mevcut hayvan varsa hata
            throw new recordAlreadyExistException(existingAnimal.getId());
        }
        animal.setId(null);
        return animalRepository.save(animal);
    }


    public Animal findById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Animal get(Long id) {

        return this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

    }

    @Override
    public boolean existsByName(String name) {
        return animalRepository.existsByName(name);
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepository.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepository.save(animal);
    }

    @Override
    public boolean delete(Long id) {

        Animal animal = this.get(id);
        this.animalRepository.delete(animal);
        return true;
    }

}
