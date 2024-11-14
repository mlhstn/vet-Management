package com.vetManagement.spring.dto.request.Doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateRequest {

    Long id;

    @NotNull(message = "please write a name")
    private String name;

    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
