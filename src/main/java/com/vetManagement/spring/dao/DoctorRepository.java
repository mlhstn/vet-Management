package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
