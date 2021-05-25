package com.CompanieTurism.services.flights;

import com.CompanieTurism.dao.FlightDao;
import com.CompanieTurism.dto.FlightDto;
import com.CompanieTurism.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightDao flightDao;

    @Autowired
    public FlightService(FlightRepository flightRepository, FlightDao flightDao) {
        this.flightRepository = flightRepository;
        this.flightDao = flightDao;
    }

    public List<FlightDto> getFlights() {
        return flightDao.getFlights();
    }

    public boolean checkExistingId(Integer flightId) {
        return this.flightRepository.existsById(flightId);
    }

    @Transactional
    public void deleteFlight(Integer flightId) {
        this.flightDao.delete(flightId);
    }
}
