package com.CompanieTurism.services.flight;

import com.CompanieTurism.dao.FlightDao;
import com.CompanieTurism.dto.FlightDto;
import com.CompanieTurism.exceptions.ErrorMessage;
import com.CompanieTurism.exceptions.FlightNotFoundException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.models.Flight;
import com.CompanieTurism.repository.FlightRepository;
import com.CompanieTurism.requests.flight.BaseFlightRegisterRequest;
import com.CompanieTurism.services.employee.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@Slf4j
public class FlightAdminService {

    private final FlightService flightService;
    private final FlightRepository flightRepository;
    private final FlightDao flightDao;
    private final EmployeeService employeeService;

    @Autowired
    public FlightAdminService(FlightService flightService,
                              FlightRepository flightRepository,
                              FlightDao flightDao,
                              EmployeeService employeeService) {
        this.flightService = flightService;
        this.flightRepository = flightRepository;
        this.flightDao = flightDao;
        this.employeeService = employeeService;
    }

    public List<FlightDto> getFlights() {
        return flightService.getFlights();
    }

    public FlightDto getFlight(Integer flightId) {
        Flight flight = this.flightRepository.findByIdAndEmployee(flightId)
                .orElseThrow(() -> new FlightNotFoundException(ErrorMessage.FLIGHT_NOT_FOUND));
        log.info("Flight request: {}", flight);
        return FlightDao.TO_FLIGHT_DTO.getDestination(flight);
    }

    @Transactional
    @SneakyThrows
    public FlightDto createFlight(BaseFlightRegisterRequest flightRequest) {
        Employee employee = this.employeeService.findEmployeeByCnp(flightRequest.getCnp());

        Flight flight = this.flightRepository.save(getUpdatedFlight(employee, flightRequest));
        return FlightDao.TO_FLIGHT_DTO.getDestination(flight);
    }

    private Flight getUpdatedFlight(Employee employee, BaseFlightRegisterRequest flightRequest) {
        Flight flight = new Flight();
        flight.setEmployee(employee);
        flight.setAirportDeparture(flightRequest.getAirportDeparture());
        flight.setDateOfDeparture(flightRequest.getDateOfDeparture());
        flight.setAirportArrival(flightRequest.getAirportArrival());
        flight.setDateOfArrival(flightRequest.getDateOfArrival());
        flight.setCompany(flightRequest.getCompany());

        return flight;
    }

    @Transactional
    public FlightDto updateFlight(Integer flightId, BaseFlightRegisterRequest flightRequest) {
        if (!this.flightService.checkExistingId(flightId)) {
            log.info("Flight with id {} not found.", flightId);
            throw new FlightNotFoundException(ErrorMessage.FLIGHT_NOT_FOUND);
        }

        int res = this.flightRepository.updateFlight(flightId, flightRequest.getAirportDeparture(),
                flightRequest.getDateOfDeparture(), flightRequest.getAirportArrival(),
                flightRequest.getDateOfArrival(), flightRequest.getCompany());

        if (res < 1) {
            log.info("Cannot update flight with id: {}", flightId);
            throw new PersistenceException("Cannot update flight with id: " + flightId);
        }
        log.info("Flight with id {} has been updated with payload {}", flightId, flightRequest);

        Flight flight = this.flightRepository.findByIdAndEmployee(flightId)
                .orElseThrow(() -> new FlightNotFoundException(ErrorMessage.FLIGHT_NOT_FOUND));

        return FlightDao.TO_FLIGHT_DTO.getDestination(flight);
    }

    @Transactional
    public void deleteFlight(Integer flightId) {
        if (!this.flightService.checkExistingId(flightId)) {
            log.info("Flight with id {} not found.", flightId);
            throw new FlightNotFoundException(ErrorMessage.FLIGHT_NOT_FOUND);
        }

        this.flightDao.delete(flightId);
        log.info("Flight with id {} has been deleted!", flightId);
    }

    @Transactional
    public void deleteFlightBasedOnEmployeeId(Integer employeeId) {
        List<Flight> flights = this.flightRepository.findAllByEmployeeId(employeeId);
        if (flights.isEmpty()) {
            log.info("No flights for employee id {}", employeeId);
            return;
        }

        this.flightDao.deleteAll(flights);
    }
}
