package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.services.hotel.AccommodationPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AccommodationPackageController {

    private final AccommodationPackageService accommodationPackageService;

    @Autowired
    public AccommodationPackageController(AccommodationPackageService accommodationPackageService) {
        this.accommodationPackageService = accommodationPackageService;
    }

    @GetMapping("/v1/accommodationPackages/hotel/{hotelId}")
    public ResponseEntity<Object> getAccommodationPackages(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(this.accommodationPackageService.getAccommodationPackages(hotelId));
    }
}
