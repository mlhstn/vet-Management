package com.vetManagement.spring.dao;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
