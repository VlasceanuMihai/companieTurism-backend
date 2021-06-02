package com.CompanieTurism.services.hotel;

import com.CompanieTurism.enums.PackageType;
import com.CompanieTurism.models.AccommodationPackage;
import com.CompanieTurism.requests.hotel.BaseAccommodationPackageRequest;
import com.CompanieTurism.responses.hotel.TotalPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccommodationPackageAdminService {

    private final AccommodationPackageService accommodationPackageService;

    @Autowired
    public AccommodationPackageAdminService(AccommodationPackageService accommodationPackageService) {
        this.accommodationPackageService = accommodationPackageService;
    }

    public List<AccommodationPackage> getAccommodationPackages(Integer hotelId) {
        return this.accommodationPackageService.getAccommodationPackages(hotelId);
    }

    public TotalPriceResponse generateTotalPrice(BaseAccommodationPackageRequest accommodationPackage) {
        Integer totalPrice = 0;
        switch (accommodationPackage.getPackageType()) {
            case STANDARD:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.STANDARD.getValue());
                break;
            case PREMIUM:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.PREMIUM.getValue());
                break;
            case VIP:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.VIP.getValue());
                break;
            default:
                break;
        }

        return TotalPriceResponse.builder()
                .totalPrice(totalPrice)
                .build();
    }

    private Integer calculateTotalPrice(BaseAccommodationPackageRequest accommodationPackage, Integer packageTypeValue) {
        return ((accommodationPackage.getPricePerNight() * accommodationPackage.getNightsNumber())
                * accommodationPackage.getRoomsNumber()) + packageTypeValue;
    }
}
