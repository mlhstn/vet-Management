package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IAvailableDateService;
import com.vetManagement.spring.busines.abstracts.IDoctorService;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dao.AvailableDateRepository;
import com.vetManagement.spring.dto.request.Animal.AnimalUpdateRequest;
import com.vetManagement.spring.dto.request.AvailableDate.AvailableDateSaveRequest;
import com.vetManagement.spring.dto.request.AvailableDate.AvailableDateUpdateRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.AvailableDate.AvailableDateResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.AvailableDate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/availableDates")
public class AvailableDateController {

    private final IAvailableDateService availableDateService;
    private final ImodelMapperService modelMapper;
    private final AvailableDateRepository availableDateRepository;


    public AvailableDateController(IAvailableDateService availableDateService, ImodelMapperService modelMapper, AvailableDateRepository availableDateRepository) {
        this.availableDateService = availableDateService;
        this.modelMapper = modelMapper;
        this.availableDateRepository = availableDateRepository;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@RequestBody @Validated AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        this.availableDateService.save(saveAvailableDate);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class);
        return ResultHelper.created(availableDateResponse);

    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AvailableDateResponse>> getAllAvailableDates() {
        List<AvailableDate> availableDates = availableDateService.getAllAvailableDates();
        List<AvailableDateResponse> availableDateResponses = availableDates.stream()
                .map(availableDate -> modelMapper.forResponse().map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.ok(availableDateResponses);
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ) {
        Page<AvailableDate> availableDatesPage = this.availableDateService.cursor(page,pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatesPage
                .map(animal -> this.modelMapper.forResponse().map(availableDatesPage,AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {

        AvailableDate availableDate = this.availableDateService.get(id);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){

        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        this.availableDateService.update(updateAvailableDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){

        this.availableDateService.delete(id);
        return ResultHelper.ok();

    }
}
