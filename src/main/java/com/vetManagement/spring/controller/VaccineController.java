package com.vetManagement.spring.controller;

import com.vetManagement.spring.busines.abstracts.IVaccineService;
import com.vetManagement.spring.core.config.Msg;
import com.vetManagement.spring.core.config.Result;
import com.vetManagement.spring.core.config.ResultData;
import com.vetManagement.spring.core.config.ResultHelper;
import com.vetManagement.spring.core.config.exception.NotFoundException;
import com.vetManagement.spring.core.config.exception.recordAlreadyExistException;
import com.vetManagement.spring.core.modelMapper.ImodelMapperService;
import com.vetManagement.spring.dao.AnimalRepository;
import com.vetManagement.spring.dto.request.Vaccine.VaccineSaveRequest;
import com.vetManagement.spring.dto.request.Vaccine.VaccineUpdateRequest;
import com.vetManagement.spring.dto.response.CursorResponse;
import com.vetManagement.spring.dto.response.Vaccine.VaccineResponse;
import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Vaccine;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    private final IVaccineService iVaccineService;
    private final ImodelMapperService modelMapper;
    private final AnimalRepository animalRepository;

    public VaccineController(IVaccineService iVaccineService, ImodelMapperService modelMapper, AnimalRepository animalRepository) {
        this.iVaccineService = iVaccineService;
        this.modelMapper = modelMapper;
        this.animalRepository = animalRepository;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> saveVaccine(@RequestBody @Valid VaccineSaveRequest vaccineSaveRequest) {

        Vaccine savedVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest,Vaccine.class);
        this.iVaccineService.save(savedVaccine);

        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(savedVaccine, VaccineResponse.class);
        return ResultHelper.created(vaccineResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {

        Vaccine vaccine = this.iVaccineService.get(id);

        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine,VaccineResponse.class);
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
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest){

        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        this.iVaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){

        this.iVaccineService.delete(id);
        return ResultHelper.ok();

    }

    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinationsByAnimalId(@PathVariable Long animalId) {
        try {
            List<Vaccine> vaccines = iVaccineService.getVaccinesByAnimalId(animalId);
            List<VaccineResponse> vaccineResponses = vaccines.stream()
                    .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                    .toList();
            return ResultHelper.success(vaccineResponses);
        } catch (NotFoundException e) {
            return (ResultData<List<VaccineResponse>>) ResultHelper.recordNotFoundWithId(animalId);
        }
    }
}


