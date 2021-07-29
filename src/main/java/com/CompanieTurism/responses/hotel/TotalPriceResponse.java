package com.CompanieTurism.responses.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TotalPriceResponse {

    private Integer totalPrice;

    private String currency;
}
