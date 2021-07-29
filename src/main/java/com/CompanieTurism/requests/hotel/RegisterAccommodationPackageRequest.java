package com.CompanieTurism.requests.hotel;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RegisterAccommodationPackageRequest extends BaseAccommodationPackageRequest {

    @NotNull
    private Integer totalPrice;
}
