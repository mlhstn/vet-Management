package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IVaccineService;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.dto.request.Vaccine.VaccineSaveRequest;
import com.vetManagement.spring.dto.request.Vaccine.VaccineUpdateRequest;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Vaccine.VaccineResponse;
import com.vetManagement.spring.entity.Vaccine;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final IVaccineService iVaccineService;
    private final ModelMapper modelMapper;

    public VaccineController(IVaccineService iVaccineService, ModelMapper modelMapper) {
        this.iVaccineService = iVaccineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> saveVaccine(@RequestBody @Validated VaccineSaveRequest vaccineSaveRequest) {

        Vaccine vaccine = modelMapper.map(vaccineSaveRequest, Vaccine.class);

        try {
            Vaccine savedVaccine = iVaccineService.save(vaccine);

            VaccineResponse vaccineResponse = this.modelMapper.map(savedVaccine, VaccineResponse.class);

            return ResultHelper.created(vaccineResponse);

        } catch (recordAlreadyExistException e) {

            Vaccine existingVaccine = iVaccineService.get(e.getId());

            VaccineResponse existingVaccineResponse = this.modelMapper.map(existingVaccine, VaccineResponse.class);

            return ResultHelper.recordAlreadyExistsError(e.getId(), existingVaccineResponse);
        }
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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){

        Vaccine updateVaccine = this.modelMapper.map(vaccineUpdateRequest, Vaccine.class);
        this.iVaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.map(updateVaccine, VaccineResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){

        this.iVaccineService.delete(id);
        return ResultHelper.ok();

    }


}
