package com.CompanieTurism.requests.hotel;

import com.CompanieTurism.enums.PackageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@ToString
public class BaseAccommodationPackageRequest {

    @NotNull
    private PackageType packageType;

    @NotNull
    private Integer pricePerNight;

    @NotNull
    private Integer nightsNumber;

    @NotNull
    private Integer roomsNumber;

    @NotNull
    private Integer adultsNumber;

    @NotNull
    private Integer kidsNumber;
}
