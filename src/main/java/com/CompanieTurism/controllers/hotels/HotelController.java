package com.CompanieTurism.controllers.hotels;

import com.CompanieTurism.services.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/v1/hotels")
    public ResponseEntity<Object> getHotels() {
        return ResponseEntity.ok(this.hotelService.getHotelsAndDestinations());
    }
}
