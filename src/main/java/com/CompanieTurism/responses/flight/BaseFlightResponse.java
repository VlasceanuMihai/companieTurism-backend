package com.CompanieTurism.responses.flight;

import com.CompanieTurism.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BaseFlightResponse {

    private Integer id;

    private String airportDeparture;

    private LocalDateTime dateOfDeparture;

    private String airportArrival;

    private LocalDateTime dateOfArrival;

    private String company;

    private EmployeeDto employee;
}
