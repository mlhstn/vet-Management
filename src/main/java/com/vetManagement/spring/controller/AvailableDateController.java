package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.concretes.AvailableDateManager;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class AvailableDateController {

    private final AvailableDateManager availableDateManager;

    private final ModelMapper modelMapper;

    public AvailableDateController(AvailableDateManager availableDateManager, ModelMapper modelMapper) {
        this.availableDateManager = availableDateManager;
        this.modelMapper = modelMapper;
    }
}
