package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IVaccineService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.VaccineRepository;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VaccineManager implements IVaccineService {

   private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        if (vaccineRepository.findByName(vaccine.getName()) != null){
            throw new recordAlreadyExistException(vaccineRepository.findByName(vaccine.getName()).getId());
        }
        vaccine.setId(null);
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
}
