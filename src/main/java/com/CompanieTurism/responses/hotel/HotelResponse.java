package com.CompanieTurism.responses.hotel;

import com.CompanieTurism.dto.DestinationDto;
import com.CompanieTurism.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class HotelResponse {

    private String hotelName;

    private Integer hotelRating;

    private DestinationDto destination;

    private EmployeeDto employee;
}
