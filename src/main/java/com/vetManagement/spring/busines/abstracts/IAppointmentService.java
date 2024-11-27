package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.dto.request.Appointment.AppointmentUpdateRequest;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Appointment;

public interface IAppointmentService {

    Appointment save(Appointment appointment);

    Appointment get(Long id);

    Appointment update(Appointment appointment);

    boolean delete(Long id);

}
