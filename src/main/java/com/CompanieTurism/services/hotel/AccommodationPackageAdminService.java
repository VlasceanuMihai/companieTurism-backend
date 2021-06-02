package com.CompanieTurism.services.hotel;

import com.CompanieTurism.models.AccommodationPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteAccommodationPackage(Integer hotelId) {

    }
}
