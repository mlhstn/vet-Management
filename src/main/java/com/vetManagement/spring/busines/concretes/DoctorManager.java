package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IDoctorService;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dao.DoctorRepository;
import com.vetManagement.spring.entity.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements IDoctorService {

  private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        if (doctorRepository.findByName(doctor.getName()) != null){
            throw new recordAlreadyExistException(doctorRepository.findByName(doctor.getName()).getId());
        }
        doctor.setId(null);
        return doctorRepository.save(doctor);
    }
}
