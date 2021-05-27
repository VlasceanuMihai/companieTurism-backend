package com.CompanieTurism.requests.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelRegisterRequest {

    @NotNull
    @NotBlank
    @Size(max = 13)
    private String cnp;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String hotelName;

    @NotNull
    private Integer rating;

    private DestinationRegisterRequest destination;
}
