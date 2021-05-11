package com.CompanieTurism.dto;

import com.CompanieTurism.enums.PackageType;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class AccommodationPackageDto {

    private Integer id;

    private PackageType packageType;

    @NotNull
    @NotBlank
    private Integer pricePerNight;

    @NotNull
    @NotBlank
    private Integer nightsNumber;

    @NotNull
    @NotBlank
    private Integer roomsNumber;

    @NotNull
    @NotBlank
    private Integer adultsNumber;

    private Integer kidsNumber;

    @NotNull
    @NotBlank
    private Integer totalPrice;
}
