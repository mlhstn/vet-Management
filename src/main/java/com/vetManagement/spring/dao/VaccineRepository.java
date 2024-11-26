package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccineRepository extends JpaRepository<Vaccine,Long> {

    Vaccine findByName(String name);
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByCodeAndNameAndAnimalIdAndProtectionFinishDateAfter(String code, String name, Long animalId, LocalDate protectionFinishDate);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate protectionStartDate, LocalDate protectionFinishDate);
}
