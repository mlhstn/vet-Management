package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Animal findByName(String name);

    boolean existsByName(String name);

    List<Animal> findAllByOrderByNameAsc();

    List<Animal> findByCustomerId(Long id);

    /*@Query("SELECT e FROM Event e WHERE e.id = :id")
    List<Animal> findByAnimal(@Param("id") Long id);*/
}
