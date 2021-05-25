package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class HotelAdminController {

    private final HotelService hotelService;

    @Autowired
    public HotelAdminController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/v1/hotels")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getHotels(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(this.hotelService.getHotelsAndDestinations(pageable));
    }
}
