package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.dto.request.Animal.AnimalSaveRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.entity.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(Long id);

    boolean existsByName(String name);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

    public List<AnimalResponse> getAllAnimalsSorted();

    List<AnimalResponse> getAnimalsByCustomerId(Long id);

    Animal getAnimalById(Long id);

    Animal findById(Long id);
}
