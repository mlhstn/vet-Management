package com.vetManagement.spring.busines.concretes;

import com.vetManagement.spring.busines.abstracts.IAvailableDateService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.dao.AvailableDateRepository;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.AvailableDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return availableDateRepository.save(availableDate); }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate delete(Long id) {
        return null;
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public AvailableDate findById(Long id) {
        Optional<AvailableDate> availableDate = availableDateRepository.findById(id);
        return availableDate.orElse(null);
    }

    @Override
    public List<AvailableDate> getAllAvailableDates() {
        return availableDateRepository.findAll(); }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepository.findAll(pageable);
    }


}
