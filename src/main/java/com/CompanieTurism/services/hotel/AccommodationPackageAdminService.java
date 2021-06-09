package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.AccommodationPackageDao;
import com.CompanieTurism.enums.PackageType;
import com.CompanieTurism.models.AccommodationPackage;
import com.CompanieTurism.repository.AccommodationPackageRepository;
import com.CompanieTurism.requests.hotel.BaseAccommodationPackageRequest;
import com.CompanieTurism.responses.hotel.TotalPriceResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AccommodationPackageAdminService {

    private final AccommodationPackageService accommodationPackageService;
    private final AccommodationPackageDao accommodationPackageDao;
    private final AccommodationPackageRepository accommodationPackageRepository;

    @Autowired
    public AccommodationPackageAdminService(AccommodationPackageService accommodationPackageService,
                                            AccommodationPackageDao accommodationPackageDao,
                                            AccommodationPackageRepository accommodationPackageRepository) {
        this.accommodationPackageService = accommodationPackageService;
        this.accommodationPackageDao = accommodationPackageDao;
        this.accommodationPackageRepository = accommodationPackageRepository;
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

    @Transactional
    @SneakyThrows
    public void deleteAccommodationBasedOnEmployee(Integer employeeId) {
        List<AccommodationPackage> accommodationPackages = this.accommodationPackageRepository.findAllByEmployeeId(employeeId);
        if (accommodationPackages.isEmpty()) {
            log.info("No accommodations for employee id {}", employeeId);
            return;
        }
//
        this.accommodationPackageDao.deleteAll(accommodationPackages);
    }
}
