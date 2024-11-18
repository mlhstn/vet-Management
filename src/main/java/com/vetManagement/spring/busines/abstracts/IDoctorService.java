package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDoctorService {

    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(Long id);

    List<Doctor> getAllDoctors();
}
