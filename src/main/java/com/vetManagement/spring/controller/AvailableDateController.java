package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IAvailableDateService;
import com.vetManagement.spring.busines.abstracts.IDoctorService;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.dto.request.AvailableDate.AvailableDateSaveRequest;
import com.vetManagement.spring.dto.response.AvailableDate.AvailableDateResponse;
import com.vetManagement.spring.entity.AvailableDate;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/availableDates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;
    private final ModelMapper modelMapper;
    private final IDoctorService iDoctorService;

    public AvailableDateController(IAvailableDateService availableDateService, ModelMapper modelMapper, IDoctorService iDoctorService) {
        this.availableDateService = availableDateService;

        this.modelMapper = modelMapper;
        this.iDoctorService = iDoctorService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@RequestBody @Validated AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate saveAvailableDate = this.modelMapper.map(availableDateSaveRequest, AvailableDate.class);
        this.availableDateService.save(saveAvailableDate);
        AvailableDateResponse availableDateResponse = this.modelMapper.map(saveAvailableDate, AvailableDateResponse.class);
        return ResultHelper.created(availableDateResponse);

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AvailableDateResponse>> getAllAvailableDates() {
        List<AvailableDate> availableDates = availableDateService.getAllAvailableDates();
        List<AvailableDateResponse> availableDateResponses = availableDates.stream()
                .map(availableDate -> modelMapper.map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.ok(availableDateResponses);
    }
}
