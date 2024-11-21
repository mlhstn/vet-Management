package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.AppointmentManager;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
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
