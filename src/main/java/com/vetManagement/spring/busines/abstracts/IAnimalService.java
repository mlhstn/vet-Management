package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;

public interface IAnimalService {

    Animal save(Animal animal);
    boolean existsByName(String name);
    Animal get(Long id);
    Animal findById(Long id);
}
