package com.CompanieTurism.responses.hotel;

import com.CompanieTurism.dto.AccommodationPackageDto;
import com.CompanieTurism.dto.HotelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccommodationPackageResponse {

    private AccommodationPackageDto accommodationPackage;

    private HotelDto hotel;
}
