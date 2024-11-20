package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Doctor;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    List<Vaccine> getVaccinesByAnimalId(Long animalId);

}
