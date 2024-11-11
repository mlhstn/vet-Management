package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.AnimalRepository;
import com.vetManagement.spring.entity.Animal;
import org.springframework.stereotype.Service;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
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

    @Override
    public boolean existsByName(String name) {
        return animalRepository.existsByName(name);
    }
    public Animal findById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
    @Override
    public Animal get(Long id) {

        return this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

    }
}
