package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAnimalService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.AnimalRepository;
import com.vetManagement.spring.dao.VaccineRepository;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final VaccineRepository vaccineRepository;

    public AnimalManager(AnimalRepository animalRepository,
                         VaccineRepository vaccineRepository) {
        this.animalRepository = animalRepository;
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Animal save(Animal animal) {
        Animal animalName = animalRepository.findByName(animal.getName());
        if (Objects.nonNull(animalName)) {
            // Eğer mevcut hayvan varsa hata
            throw new recordAlreadyExistException(animalName.getId());
        }
        animal.setId(null);
        return animalRepository.save(animal);
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
        Pageable pageable = PageRequest.of(page, pageSize);
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

    @Override
    public List<AnimalResponse> getAllAnimalsSorted() {

        final List<Animal> animals = animalRepository.findAllByOrderByNameAsc(); // animalName'e göre sıralama

        // Animal entity'lerini AnimalResponse DTO'suna dönüştürüyoruz
        return animals.stream()
                .map(animal -> new AnimalResponse(
                        animal.getId(),
                        animal.getName(),
                        animal.getSpecies(),
                        animal.getBreed(),
                        animal.getGender(),
                        animal.getColour(),
                        animal.getDateOfBirth()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalResponse> getAnimalsByCustomerId(Long id) {

        List<Animal> animals = animalRepository.findByCustomerId(id);

        return animals.stream().map(animal -> new AnimalResponse(
                        animal.getId(),
                        animal.getName(),
                        animal.getSpecies(),
                        animal.getGender(),
                        animal.getBreed(),
                        animal.getColour(),
                        animal.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    @Override
    public Animal getAnimalById(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);
        return animal.orElse(null);
    }


}



