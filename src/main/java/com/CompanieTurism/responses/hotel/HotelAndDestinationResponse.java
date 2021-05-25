package com.CompanieTurism.responses.hotel;

import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.enums.CovidScenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HotelAndDestinationResponse {

    private Integer hotelId;

    private String hotelName;

    private Integer hotelRating;

    private String country;

    private String city;

    @Enumerated(EnumType.STRING)
    private CovidScenario covidScenario;

    private EmployeeDto employeeDto;
}
