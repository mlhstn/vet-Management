package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Animal findByName(String name);
}
