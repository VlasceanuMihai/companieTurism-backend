package com.CompanieTurism.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class FlightDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String airportDeparture;

    @NotNull
    @NotBlank
    @FutureOrPresent
    private LocalDateTime dateOfDeparture;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String airportArrival;

    @NotNull
    @NotBlank
    @FutureOrPresent
    private LocalDateTime dateOfArrival;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String company;
}
