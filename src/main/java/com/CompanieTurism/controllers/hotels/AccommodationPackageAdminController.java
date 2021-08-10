package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.requests.hotel.BaseAccommodationPackageRequest;
import com.CompanieTurism.requests.hotel.RegisterAccommodationPackageRequest;
import com.CompanieTurism.services.hotel.AccommodationPackageAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AccommodationPackageAdminController {

    private final AccommodationPackageAdminService accommodationPackageService;

    @Autowired
    public AccommodationPackageAdminController(AccommodationPackageAdminService accommodationPackageService) {
        this.accommodationPackageService = accommodationPackageService;
    }

    @PostMapping("/v1/generateTotalPrice")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> generateTotalPrice(@Valid @RequestBody BaseAccommodationPackageRequest accommodationPackageRequest) {
        return ResponseEntity.ok(this.accommodationPackageService.generateTotalPrice(accommodationPackageRequest));
    }

    @PostMapping("/v1/accommodationPackage/create/hotel/{hotelId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createAccommodationPackage(@PathVariable Integer hotelId,
                                                             @Valid @RequestBody RegisterAccommodationPackageRequest accommodationPackageRequest) {
        return ResponseEntity.ok(this.accommodationPackageService.createAccommodationPackage(hotelId, accommodationPackageRequest));
    }

    @PutMapping("/v1/accommodationPackage/update/{accommodationPackageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateAccommodationPackage(@PathVariable Integer accommodationPackageId,
                                                             @Valid @RequestBody RegisterAccommodationPackageRequest accommodationPackageRequest) {
        return ResponseEntity.ok(this.accommodationPackageService.updateAccommodationPackage(accommodationPackageId, accommodationPackageRequest));
    }

    @DeleteMapping("/v1/accommodationPackage/delete/{accommodationPackageId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteAccommodationPackage(@PathVariable Integer accommodationPackageId) {
        this.accommodationPackageService.delete(accommodationPackageId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
