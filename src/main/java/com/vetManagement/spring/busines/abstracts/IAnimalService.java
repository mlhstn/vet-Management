package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import org.springframework.data.domain.Page;

public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(Long id);

    boolean existsByName(String name);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

}
