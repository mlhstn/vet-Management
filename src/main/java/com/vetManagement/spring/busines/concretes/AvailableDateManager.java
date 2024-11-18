package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAvailableDateService;
import com.vetManagement.spring.dao.AvailableDateRepository;
import com.vetManagement.spring.entity.AvailableDate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepository.save(availableDate); }

    public List<AvailableDate> getAllAvailableDates() {
        return availableDateRepository.findAll(); }


}
