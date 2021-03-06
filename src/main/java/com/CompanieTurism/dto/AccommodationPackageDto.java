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

    private Integer kidsNumber;

    @NotNull
    private Integer totalPrice;
}
