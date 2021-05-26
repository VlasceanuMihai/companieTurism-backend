package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.DestinationDao;
import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dao.HotelDao;
import com.CompanieTurism.dto.DestinationDto;
import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.exceptions.HotelExistsException;
import com.CompanieTurism.models.Destination;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.CompanieTurism.requests.hotel.HotelRegisterRequest;
import com.CompanieTurism.responses.hotel.HotelResponse;
import com.CompanieTurism.services.employee.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HotelAdminService {

    private final HotelDao hotelDao;
    private final HotelRepository hotelRepository;
    private final EmployeeService employeeService;
    private final EmployeeDao employeeDao;
    private final DestinationDao destinationDao;

    @Autowired
    public HotelAdminService(HotelDao hotelDao,
                             HotelRepository hotelRepository,
                             EmployeeService employeeService,
                             EmployeeDao employeeDao,
                             DestinationDao destinationDao) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.employeeService = employeeService;
        this.employeeDao = employeeDao;
        this.destinationDao = destinationDao;
    }

    public List<HotelResponse> getHotelsAndDestinations(Pageable pageable) {
        List<Hotel> hotels = this.hotelRepository.findHotels(pageable);

        List<HotelResponse> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelResponse newHotel = HotelResponse.builder()
                    .hotelName(hotel.getName())
                    .hotelRating(hotel.getRating())
                    .destination(DestinationDao.TO_DESTINATION_DTO.getDestination(hotel.getDestination()))
                    .employee(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(hotel.getDestination().getEmployee()))
                    .build();
            response.add(newHotel);
        }
        return response;
    }

    @Transactional
    @SneakyThrows
    public HotelResponse createHotelWithDestination(HotelRegisterRequest hotelRequest) {
        Employee employee = this.employeeService.findEmployeeByCnp(hotelRequest.getCnp());

        boolean count = this.hotelDao.countDuplicateHotel(employee.getId(), hotelRequest.getHotelName(),
                hotelRequest.getDestination().getCountry(), hotelRequest.getDestination().getCity());

        if (count) {
            throw new HotelExistsException("Employee id: " + employee.getId() + " is already assigned to Hotel: " +
                    hotelRequest.getHotelName() + ", country: " + hotelRequest.getDestination().getCountry() +
                    ", city: " + hotelRequest.getDestination().getCity());
        }

        Destination destination = Destination.builder()
                .employee(employee)
                .country(hotelRequest.getDestination().getCountry())
                .city(hotelRequest.getDestination().getCity())
                .covidScenario(hotelRequest.getDestination().getCovidScenario())
                .build();

        DestinationDto savedDestination = this.destinationDao.save(destination);

        Hotel hotel = Hotel.builder()
                .destination(destination)
                .name(hotelRequest.getHotelName())
                .rating(hotelRequest.getRating())
                .build();

        this.hotelDao.save(hotel);

        return HotelResponse.builder()
                .hotelName(hotel.getName())
                .hotelRating(hotel.getRating())
                .destination(savedDestination)
                .employee(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(employee))
                .build();
    }
}
