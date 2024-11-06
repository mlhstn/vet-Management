package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {

    Vaccine findByName(String name);
}
