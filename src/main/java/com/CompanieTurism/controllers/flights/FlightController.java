package com.CompanieTurism.controllers.flights;

import com.CompanieTurism.services.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/v1/flights")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getFlights() {
        return ResponseEntity.ok(this.flightService.getFlights());
    }
}
