package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.DoctorManager;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class DoctorController {

    private final DoctorManager doctorManager;
    private final ModelMapper modelMapper;

    public DoctorController(DoctorManager doctorManager, ModelMapper modelMapper) {
        this.doctorManager = doctorManager;
        this.modelMapper = modelMapper;
    }
}
