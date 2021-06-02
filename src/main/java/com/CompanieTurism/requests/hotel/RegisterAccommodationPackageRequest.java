package com.CompanieTurism.requests.hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RegisterAccommodationPackageRequest extends BaseAccommodationPackageRequest {

    @NotNull
    @NotBlank
    private Integer totalPrice;
}
