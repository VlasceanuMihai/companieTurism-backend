package com.CompanieTurism.services.flights;

import com.CompanieTurism.dao.FlightDao;
import com.CompanieTurism.dto.FlightDto;
import com.CompanieTurism.exceptions.FlightNotFoundException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.models.Flight;
import com.CompanieTurism.repository.FlightRepository;
import com.CompanieTurism.requests.flight.BaseFlightRequest;
import com.CompanieTurism.responses.flights.BaseFlightResponse;
import com.CompanieTurism.services.employee.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BaseFlightResponse createFlight(BaseFlightRequest flightRequest) {
        return this.saveFlight(flightRequest);
    }

    private BaseFlightResponse saveFlight(BaseFlightRequest flightRequest){
        Employee employee = this.employeeService.findEmployeeByCnp(flightRequest.getCnp());

        FlightDto flightDto = this.flightDao.save(getUpdatedFlight(employee, flightRequest));

        return BaseFlightResponse.builder()
                .employeeId(employee.getId())
                .flightDto(flightDto)
                .build();
    }

    private Flight getUpdatedFlight(Employee employee, BaseFlightRequest flightRequest) {
        Flight flight = new Flight();
        flight.setEmployee(employee);
        flight.setAirportDeparture(flightRequest.getAirportDeparture());
        flight.setDateOfDeparture(flightRequest.getDateOfDeparture());
        flight.setAirportArrival(flightRequest.getAirportArrival());
        flight.setDateOfArrival(flightRequest.getDateOfArrival());
        flight.setCompany(flightRequest.getCompany());

        return flight;
    }

    public BaseFlightResponse updateFlight(Integer flightId, BaseFlightRequest flightRequest) {
        if (!this.flightService.checkExistingId(flightId)){
            throw new FlightNotFoundException("Flight with id " + flightId + " not found!");
        }

        return this.saveFlight(flightRequest);
    }
}
