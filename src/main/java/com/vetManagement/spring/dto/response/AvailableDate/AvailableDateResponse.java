package com.vetManagement.spring.dto.response.AvailableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {

    private Long id;

    private LocalDate availableDate;
}
