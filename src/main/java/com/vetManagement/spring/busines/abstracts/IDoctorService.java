package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import com.vetManagement.spring.entity.Doctor;

public interface IDoctorService {

    Doctor save(Doctor doctor);
    Doctor get(Long id);
}
