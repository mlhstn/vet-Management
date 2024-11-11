package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAppointmentService;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import com.vetManagement.spring.dao.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
