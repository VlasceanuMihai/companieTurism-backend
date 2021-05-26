package com.CompanieTurism.requests.hotel;

import com.CompanieTurism.enums.CovidScenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DestinationRegisterRequest {

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String country;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String city;

    @NotNull
    @NotBlank
    private CovidScenario covidScenario;
}
