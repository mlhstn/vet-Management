package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.AppointmentManager;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;


@Controller
public class AppoinmentController {

    private final AppointmentManager appointmentManager;
    private final ModelMapper modelMapper;

    public AppoinmentController(AppointmentManager appointmentManager, ModelMapper modelMapper) {
        this.appointmentManager = appointmentManager;
        this.modelMapper = modelMapper;
    }
}
