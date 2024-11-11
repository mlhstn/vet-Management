package com.vetManagement.spring.dto.response.Customer;

import com.vetManagement.spring.dto.response.Animal.AnimalResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;

    private String customerName;

    private String phone;

    private String mail;

    private String address;

    private String city;

    private List<AnimalResponse> animalList;
}
