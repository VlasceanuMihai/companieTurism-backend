package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.services.hotel.AccommodationPackageAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AccommodationPackageAdminController {

    private final AccommodationPackageAdminService accommodationPackageService;

    @Autowired
    public AccommodationPackageAdminController(AccommodationPackageAdminService accommodationPackageService) {
        this.accommodationPackageService = accommodationPackageService;
    }

    @GetMapping("/v1/accommodationPackages/{hotelId}")
    public ResponseEntity<Object> getAccommodationPackages(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(this.accommodationPackageService.getAccommodationPackages(hotelId));
    }
}
