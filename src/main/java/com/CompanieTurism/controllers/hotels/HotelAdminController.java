package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.requests.hotel.HotelRegisterRequest;
import com.CompanieTurism.services.hotel.HotelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelAdminController {

    private final HotelAdminService hotelAdminService;

    @Autowired
    public HotelAdminController(HotelAdminService hotelAdminService) {
        this.hotelAdminService = hotelAdminService;
    }

    @GetMapping("/v1/hotels")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getHotelsByPageable(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(this.hotelAdminService.getHotelsAndDestinationsByPageable(pageable));
    }

    @GetMapping("/v1/getAllHotels")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getHotels() {
        return ResponseEntity.ok(this.hotelAdminService.getHotelsAndDestinations());
    }

    @PostMapping("/v1/createHotel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createHotel(@Valid @RequestBody HotelRegisterRequest hotelRequest) {
        return ResponseEntity.ok(this.hotelAdminService.createHotelWithDestination(hotelRequest));
    }

    @DeleteMapping("/v1/deleteHotel/{hotelId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteHotel(@PathVariable Integer hotelId) {
        this.hotelAdminService.deleteHotel(hotelId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
