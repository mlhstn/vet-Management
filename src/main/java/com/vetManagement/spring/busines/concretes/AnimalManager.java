package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
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
        if (animalRepository.findByName(animal.getName()) != null) {
            throw new recordAlreadyExistException(animalRepository.findByName(animal.getName()).getId());
        }
        animal.setId(null);
        return animalRepository.save(animal);
    }
}
