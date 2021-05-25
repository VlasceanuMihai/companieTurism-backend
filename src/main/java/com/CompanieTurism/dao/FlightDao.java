package com.CompanieTurism.dao;

import com.CompanieTurism.dto.FlightDto;
import com.CompanieTurism.models.Flight;
import com.CompanieTurism.repository.FlightRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightDao {

    private static final JMapper<FlightDto, Flight> TO_FLIGHT_DTO = new JMapper<>(FlightDto.class, Flight.class);
    private static final JMapper<Flight, FlightDto> TO_FLIGHT_ENTITY = new JMapper<>(Flight.class, FlightDto.class);

    private final FlightRepository flightRepository;

    @Autowired
    public FlightDao(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Transactional(readOnly = true)
    public List<FlightDto> getFlights() {
        return this.flightRepository.findAll().stream()
                .map(TO_FLIGHT_DTO::getDestination)
                .collect(Collectors.toList());
    }

    @Transactional
    public FlightDto save(Flight flight){
        return TO_FLIGHT_DTO.getDestination(this.flightRepository.save(flight));
    }
}
