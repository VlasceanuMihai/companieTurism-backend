package com.CompanieTurism.controllers.flights;

import com.CompanieTurism.requests.flight.BaseFlightRegisterRequest;
import com.CompanieTurism.services.flight.FlightAdminService;
import com.CompanieTurism.services.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightAdminController {

    private final FlightAdminService flightAdminService;
    private final FlightService flightService;

    @Autowired
    public FlightAdminController(FlightAdminService flightAdminService, FlightService flightService) {
        this.flightAdminService = flightAdminService;
        this.flightService = flightService;
    }

    @GetMapping("/v1/flight/{flightId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getFlight(@PathVariable Integer flightId) {
        return ResponseEntity.ok(this.flightAdminService.getFlight(flightId));
    }

    @PostMapping("/v1/createFlight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createFlight(@Valid @RequestBody BaseFlightRegisterRequest flightRequest) {
        return ResponseEntity.ok(this.flightAdminService.createFlight(flightRequest));
    }

    @PutMapping("/v1/updateFlight/{flightId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateFlight(@PathVariable Integer flightId,
                                               @Valid @RequestBody BaseFlightRegisterRequest flightRequest) {
        return ResponseEntity.ok(this.flightAdminService.updateFlight(flightId, flightRequest));
    }

    @DeleteMapping("/v1/deleteFlight/{flightId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deletefLIGHT(@PathVariable Integer flightId) {
        this.flightAdminService.deleteFlight(flightId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
