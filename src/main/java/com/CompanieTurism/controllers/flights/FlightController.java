package com.CompanieTurism.controllers.flights;

import com.CompanieTurism.requests.flight.BaseFlightRequest;
import com.CompanieTurism.services.flights.FlightAdminService;
import com.CompanieTurism.services.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class FlightController {

    private final FlightAdminService flightAdminService;
    private final FlightService flightService;

    @Autowired
    public FlightController(FlightAdminService flightAdminService, FlightService flightService) {
        this.flightAdminService = flightAdminService;
        this.flightService = flightService;
    }

    @GetMapping("/v1/flights")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getFlights() {
        return ResponseEntity.ok(this.flightService.getFlights());
    }

    @PostMapping("/v1/createFlight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createFlight(@Valid @RequestBody BaseFlightRequest flightRequest) {
        return ResponseEntity.ok(this.flightAdminService.createFlight(flightRequest));
    }

    @PutMapping("/v1/updateFlight/{flightId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateFlight(@PathVariable Integer flightId,
                                               @Valid @RequestBody BaseFlightRequest flightRequest) {
        return ResponseEntity.ok(this.flightAdminService.updateFlight(flightId, flightRequest));
    }
}
