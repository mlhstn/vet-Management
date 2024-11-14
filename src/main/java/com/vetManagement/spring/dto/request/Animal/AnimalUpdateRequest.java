package com.vetManagement.spring.dto.request.Animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalUpdateRequest {

    @Positive(message = "must be positive")
    @NotNull
    private Long id;

    @NotNull(message = "please write a name")
    private String name;

    private String species;

    private String breed;

    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    private Long customerId;
}
