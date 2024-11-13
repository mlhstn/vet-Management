package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IDoctorService;
import com.vetManagement.spring.busines.concretes.DoctorManager;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ModelMapperConfig;
import com.vetManagement.spring.dto.request.Doctor.DoctorSaveRequest;
import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Doctor.DoctorResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final IDoctorService iDoctorService;
    private final ModelMapper modelMapper;


    public DoctorController(IDoctorService iDoctorService, ModelMapper modelMapper) {
        this.iDoctorService = iDoctorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> saveDoctor(@RequestBody @Validated DoctorSaveRequest doctorSaveRequest){

        Doctor doctor = modelMapper.map(doctorSaveRequest, Doctor.class);

        try {
            Doctor savedDoctor = iDoctorService.save(doctor);

            DoctorResponse doctorResponse = this.modelMapper.map(savedDoctor, DoctorResponse.class);

            return ResultHelper.created(doctorResponse);

        }catch (recordAlreadyExistException e){
            Doctor existingDoctor = iDoctorService.get(e.getId());

            DoctorResponse existingDoctorResponse  = this.modelMapper.map(existingDoctor, DoctorResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingDoctorResponse);
        }
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id) {

        Doctor doctor = this.iDoctorService.get(id);
        DoctorResponse doctorResponse = this.modelMapper.map(doctor,DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false,defaultValue = "10") int pageSize
    ) {
        Page<Doctor> doctorPage = this.iDoctorService.cursor(page,pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.map(doctor,DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

}
