package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.requests.hotel.HotelRegisterRequest;
import com.CompanieTurism.services.hotel.HotelAdminService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/v1/hotel/{hotelId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getHotel(@PathVariable Integer hotelId) {
        return ResponseEntity.ok(this.hotelAdminService.getHotel(hotelId));
    }

    @PostMapping("/v1/createHotel")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createHotel(@Valid @RequestBody HotelRegisterRequest hotelRequest) {
        return ResponseEntity.ok(this.hotelAdminService.createHotelWithDestination(hotelRequest));
    }

    @PutMapping("/v1/updateHotel/{hotelId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateHotel(@PathVariable Integer hotelId,
                                              @Valid @RequestBody HotelRegisterRequest hotelRequest) {
        return ResponseEntity.ok(this.hotelAdminService.updateHotel(hotelId, hotelRequest));
    }

    @DeleteMapping("/v1/deleteHotel/{hotelId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteHotel(@PathVariable Integer hotelId) {
        this.hotelAdminService.deleteHotel(hotelId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
