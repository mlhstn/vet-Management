package com.vetManagement.spring.busines.abstracts;

import com.vetManagement.spring.entity.Animal;
import com.vetManagement.spring.entity.Customer;
import com.vetManagement.spring.entity.Vaccine;
import org.springframework.data.domain.Page;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);
    Vaccine get(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
}
