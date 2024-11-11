package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAvailableDateService;
import com.vetManagement.spring.dao.AvailableDateRepository;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }
}
