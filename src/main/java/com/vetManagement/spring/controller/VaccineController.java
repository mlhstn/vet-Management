package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.VaccineManager;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class VaccineController {

    private final VaccineManager vaccineManager;
    private final ModelMapper modelMapper;

    public VaccineController(VaccineManager vaccineManager, ModelMapper modelMapper) {
        this.vaccineManager = vaccineManager;
        this.modelMapper = modelMapper;
    }
}
