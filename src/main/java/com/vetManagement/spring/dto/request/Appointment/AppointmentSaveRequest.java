package com.vetManagement.spring.dto.request.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {

    private Long id;

    private LocalDate appointmentDate;

    private Long animalId;

    private Long doctorId;

    private Long CustomerId;


}
