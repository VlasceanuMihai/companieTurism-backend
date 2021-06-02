package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.requests.hotel.BaseAccommodationPackageRequest;
import com.CompanieTurism.services.hotel.AccommodationPackageAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @GetMapping("/v1/generateTotalPrice")
    public ResponseEntity<Object> generateTotalPrice(@Valid @RequestBody BaseAccommodationPackageRequest accommodationPackageRequest) {
        return ResponseEntity.ok(this.accommodationPackageService.generateTotalPrice(accommodationPackageRequest));
    }

//    @PostMapping("/v1/createAccommodationPackage")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Object> createAccommodationPackage(@Valid @RequestBody RegisterAccommodationPackageRequest accommodationPackageRequest) {
//
//    }

}
