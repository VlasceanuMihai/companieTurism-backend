package com.CompanieTurism.dto;

import com.CompanieTurism.enums.CovidScenario;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class DestinationDto {

    private Integer id;

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
