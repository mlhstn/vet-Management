package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IDoctorService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
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
       Doctor existingDoctor = doctorRepository.findByName(doctor.getName());

       if (existingDoctor != null) {
       throw  new recordAlreadyExistException(existingDoctor.getId());
       }
        doctor.setId(null);
       return doctorRepository.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
}








