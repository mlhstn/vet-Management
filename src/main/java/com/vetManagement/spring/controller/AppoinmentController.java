package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IAppointmentService;
import com.vetManagement.spring.busines.concretes.AppointmentManager;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dto.request.Appointment.AppointmentSaveRequest;
import com.vetManagement.spring.dto.request.Appointment.AppointmentUpdateRequest;
import com.vetManagement.spring.dto.request.Customer.CustomerSaveRequest;
import com.vetManagement.spring.dto.request.Customer.CustomerUpdateRequest;
import com.vetManagement.spring.dto.response.Appointment.AppointmentResponse;
import com.vetManagement.spring.dto.response.Customer.CustomerResponse;
import com.vetManagement.spring.entity.Appointment;
import com.vetManagement.spring.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/appointments")
public class AppoinmentController {

    private final IAppointmentService iAppointmentService;
    private final ImodelMapperService modelMapper;

    public AppoinmentController(IAppointmentService iAppointmentService, ImodelMapperService modelMapper) {
        this.iAppointmentService = iAppointmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@RequestBody @Validated AppointmentSaveRequest appointmentSaveRequest){

        Appointment appointment = modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);

        try {
            Appointment savedAppointment = iAppointmentService.save(appointment);

            AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(savedAppointment, AppointmentResponse.class);

            return ResultHelper.created(appointmentResponse);

        }catch (recordAlreadyExistException e){
            Appointment existingAppointment = iAppointmentService.get(e.getId());

            AppointmentResponse existingAppointmentresponse  = this.modelMapper.forResponse().map(existingAppointment, AppointmentResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingAppointmentresponse);
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest){

        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        this.iAppointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {

        this.iAppointmentService.delete(id);
        return ResultHelper.ok();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {

        Appointment appointment = this.iAppointmentService.get(id);
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(appointment,AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);
    }




}
