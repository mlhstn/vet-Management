package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.AvailableDate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate);

    AvailableDate update(AvailableDate availableDate);

    AvailableDate delete(Long id);

    AvailableDate get(Long id);

    AvailableDate findById(Long id);

    List<AvailableDate> getAllAvailableDates();

    Page<AvailableDate> cursor(int page, int pageSize);



}
