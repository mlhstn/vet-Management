package com.vetManagement.spring.dto.request.AvailableDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableDateSaveRequest {

    private Long id;

    private LocalDate availableDate;

    private Long doctorId;
}
