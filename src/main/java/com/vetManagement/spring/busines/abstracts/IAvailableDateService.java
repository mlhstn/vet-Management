package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);

    List<AvailableDate> getAllAvailableDates();
}
