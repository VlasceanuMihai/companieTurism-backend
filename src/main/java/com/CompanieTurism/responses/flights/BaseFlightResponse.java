package com.CompanieTurism.responses.flights;

import com.CompanieTurism.dto.FlightDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BaseFlightResponse {

    private FlightDto flightDto;

    private Integer employeeId;
}
