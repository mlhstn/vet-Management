package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IVaccineService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.VaccineRepository;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class VaccineManager implements IVaccineService {

   private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public boolean canAddVaccine(Vaccine vaccine) {
        LocalDate today = LocalDate.now(); List<Vaccine> existingVaccines = vaccineRepository.
        findByCodeAndNameAndAnimalIdAndProtectionFinishDateAfter( vaccine.getCode(), vaccine.getName(), vaccine.getAnimal().getId(), today);
        return existingVaccines.isEmpty();
    }

    public Vaccine save(Vaccine vaccine) {
        if (!canAddVaccine(vaccine)) {
            throw new recordAlreadyExistException(vaccine.getId());
        } vaccine.setId(null);
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine get(Long id) {

        return this.vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepository.findAll(pageable);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.get(vaccine.getId());
        return this.vaccineRepository.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.get(id);
        this.vaccineRepository.delete(vaccine);
        return true;
    }

    @Override
    public List<Vaccine> getVaccinesByAnimalId(Long animalId) {

        return vaccineRepository.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> getVaccinationsByDateRange(LocalDate protectionStartDate, LocalDate protectionFinishDate) {
        return vaccineRepository.findByProtectionFinishDateBetween(protectionStartDate, protectionFinishDate);
    }


}
