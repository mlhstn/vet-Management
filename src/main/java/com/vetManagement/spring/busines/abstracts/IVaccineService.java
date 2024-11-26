package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Doctor;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    List<Vaccine> getVaccinesByAnimalId(Long animalId);

    boolean canAddVaccine(Vaccine vaccine);

    List<Vaccine> getVaccinationsByDateRange(LocalDate protectionStartDate, LocalDate protectionFinishDate);

}
