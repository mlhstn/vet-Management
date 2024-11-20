package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.AppointmentManager;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;


@Controller
public class AppoinmentController {

    private final AppointmentManager appointmentManager;
    private final ImodelMapperService modelMapper;

    public AppoinmentController(AppointmentManager appointmentManager, ImodelMapperService modelMapper) {
        this.appointmentManager = appointmentManager;
        this.modelMapper = modelMapper;
    }
}
