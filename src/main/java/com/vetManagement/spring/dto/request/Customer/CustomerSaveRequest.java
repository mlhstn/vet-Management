package com.vetManagement.spring.dto.request.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "please write a name")
    private String customerName;

    private String phone;

    @Email
    private String mail;

    private String address;

    private String city;
}
