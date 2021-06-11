package com.CompanieTurism.services.flight;

import com.CompanieTurism.dao.FlightDao;
import com.CompanieTurism.dto.FlightDto;
import com.CompanieTurism.exceptions.FlightNotFoundException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.models.Flight;
import com.CompanieTurism.repository.FlightRepository;
import com.CompanieTurism.requests.flight.BaseFlightRegisterRequest;
import com.CompanieTurism.responses.flight.BaseFlightResponse;
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

    @Transactional
    @SneakyThrows
    public BaseFlightResponse createFlight(BaseFlightRegisterRequest flightRequest) {
        Employee employee = this.employeeService.findEmployeeByCnp(flightRequest.getCnp());

        FlightDto flightDto = this.flightDao.save(getUpdatedFlight(employee, flightRequest));

        return BaseFlightResponse.builder()
                .employeeId(employee.getId())
                .flightDto(flightDto)
                .build();
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
    public BaseFlightResponse updateFlight(Integer flightId, BaseFlightRegisterRequest flightRequest) {
        FlightDto flightDto = this.flightDao.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight with id " + flightId + " not found!"));

        Employee employee = this.employeeService.findEmployeeByCnp(flightRequest.getCnp());

        int res = this.flightRepository.updateFlight(flightId, flightRequest.getAirportDeparture(),
                flightRequest.getDateOfDeparture(), flightRequest.getAirportArrival(),
                flightRequest.getDateOfArrival(), flightRequest.getCompany());

        if (res < 1) {
            throw new PersistenceException("Cannot update flight with id: " + flightId);
        }
        log.info("Flight with id {} has been updated with payload {}", flightId, flightRequest);

        return BaseFlightResponse.builder()
                .employeeId(employee.getId())
                .flightDto(flightDto)
                .build();
    }

    @Transactional
    public void deleteFlight(Integer flightId) {
        this.flightService.deleteFlight(flightId);
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
