package com.vetManagement.spring.dto.request.Doctor;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorSaveRequest {
    @NotNull(message = "please write a name")
    private String name;

    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
