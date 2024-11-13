package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IVaccineService;
import com.vetManagement.spring.busines.concretes.VaccineManager;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Doctor.DoctorResponse;
import com.vetManagement.spring.dto.response.Vaccine.VaccineResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Doctor;
import com.vetManagement.spring.entity.Vaccine;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class VaccineController {

    private final IVaccineService iVaccineService;
    private final ModelMapper modelMapper;

    public VaccineController(IVaccineService iVaccineService, ModelMapper modelMapper) {
        this.iVaccineService = iVaccineService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {

        Vaccine vaccine = this.iVaccineService.get(id);

        VaccineResponse vaccineResponse = this.modelMapper.map(vaccine,VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ) {
        Page<Vaccine> vaccinePage = this.iVaccineService.cursor(page,pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.map(vaccine,VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }


}
