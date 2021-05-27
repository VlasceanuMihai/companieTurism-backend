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
import com.CompanieTurism.repository.DestinationRepository;
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
import java.util.Optional;

@Service
@Slf4j
public class HotelAdminService {

    private final HotelDao hotelDao;
    private final HotelRepository hotelRepository;
    private final EmployeeService employeeService;
    private final EmployeeDao employeeDao;
    private final DestinationDao destinationDao;
    private final DestinationRepository destinationRepository;

    @Autowired
    public HotelAdminService(HotelDao hotelDao,
                             HotelRepository hotelRepository,
                             EmployeeService employeeService,
                             EmployeeDao employeeDao,
                             DestinationDao destinationDao,
                             DestinationRepository destinationRepository) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.employeeService = employeeService;
        this.employeeDao = employeeDao;
        this.destinationDao = destinationDao;
        this.destinationRepository = destinationRepository;
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

        String hotelName = hotelRequest.getHotelName();
        String country = hotelRequest.getDestination().getCountry();
        String city = hotelRequest.getDestination().getCity();

        boolean count = this.hotelDao.countDuplicateHotelBasedOnEmployee(employee.getId(), hotelName, country, city);

        if (count) {
            throw new HotelExistsException("Employee id: " + employee.getId() + " is already assigned to Hotel: " +
                    hotelName + ", country: " + country + ", city: " + city);
        }

        Destination destination;
        DestinationDto savedDestinationDto;
        if (!this.destinationRepository.existsByCountryAndCity(country, city)) {
            destination = Destination.builder()
                    .employee(employee)
                    .country(country)
                    .city(city)
                    .covidScenario(hotelRequest.getDestination().getCovidScenario())
                    .build();

            savedDestinationDto = this.destinationDao.save(destination);
            log.info("Destination with country: {} and city: {} is saved!", country, city);
        } else {
            destination = this.destinationRepository.findByCountryAndCity(country, city);
            savedDestinationDto = DestinationDao.TO_DESTINATION_DTO.getDestination(destination);
            log.info("Destination {} already exists!", savedDestinationDto);
        }

        Optional<Hotel> optionalHotel = this.hotelRepository.findByNameAndDestination(hotelName, destination);
        Hotel hotel;
        if (optionalHotel.isPresent()) {
            hotel = optionalHotel.get();
            log.info("Hotel with name: {} and destination: {} is already saved!", hotelName, savedDestinationDto);
        } else {
            hotel = Hotel.builder()
                    .destination(destination)
                    .name(hotelName)
                    .rating(hotelRequest.getRating())
                    .build();

            HotelDto savedHotelDto = this.hotelDao.save(hotel);
            log.info("Hotel {} is saved!", savedHotelDto);
        }

        return HotelResponse.builder()
                .hotelName(hotelName)
                .hotelRating(hotel.getRating())
                .destination(savedDestinationDto)
                .employee(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(employee))
                .build();
    }
}