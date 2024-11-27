package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAppointmentService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import com.vetManagement.spring.dao.AnimalRepository;
import com.vetManagement.spring.dao.AppointmentRepository;
import com.vetManagement.spring.dao.DoctorRepository;
import com.vetManagement.spring.dto.request.Appointment.AppointmentUpdateRequest;
import com.vetManagement.spring.entity.*;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, AnimalRepository animalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepository.delete(appointment);
        return true;
    }
}
