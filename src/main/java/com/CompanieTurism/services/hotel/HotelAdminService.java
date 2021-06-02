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
    private final HotelService hotelService;
    private final EmployeeService employeeService;
    private final DestinationDao destinationDao;
    private final DestinationRepository destinationRepository;
    private final AccommodationPackageService accommodationPackageService;

    @Autowired
    public HotelAdminService(HotelDao hotelDao,
                             HotelRepository hotelRepository,
                             HotelService hotelService,
                             EmployeeService employeeService,
                             DestinationDao destinationDao,
                             DestinationRepository destinationRepository,
                             AccommodationPackageService accommodationPackageService) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
        this.employeeService = employeeService;
        this.destinationDao = destinationDao;
        this.destinationRepository = destinationRepository;
        this.accommodationPackageService = accommodationPackageService;
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

        boolean checkExistingHotel = this.hotelDao.countDuplicateHotelBasedOnEmployee(employee.getId(), hotelName, country, city);

        if (checkExistingHotel) {
            throw new HotelExistsException(" Hotel: " + hotelName + ", country: " + country + ", city: " + city +
                    " is already assigned to an employee id: " + employee.getId());
        }

        Destination destination;
        DestinationDto savedDestinationDto;
        if (!this.destinationRepository.existsByEmployeeAndCountryAndCity(employee, country, city)) {
            destination = Destination.builder()
                    .employee(employee)
                    .country(country)
                    .city(city)
                    .covidScenario(hotelRequest.getDestination().getCovidScenario())
                    .build();

            savedDestinationDto = this.destinationDao.save(destination);
            log.info("Destination with country: {} and city: {} is saved!", country, city);
        } else {
            destination = this.destinationRepository.findByEmployeeAndCountryAndCity(employee, country, city);
            savedDestinationDto = DestinationDao.TO_DESTINATION_DTO.getDestination(destination);
            log.info("Destination {} already exists!", savedDestinationDto);
        }

        Optional<Hotel> optionalHotel = this.hotelRepository.findByNameAndDestination(hotelName, destination);
        Hotel hotel;
        if (optionalHotel.isPresent()) {
            hotel = optionalHotel.get();
            log.info("Hotel with name: {} and destination: {} is already saved for employee id: {}!",
                    hotelName, savedDestinationDto, employee.getId());
            throw new HotelExistsException("Hotel with id: " + hotel.getId() + " is already saved!");
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

    @Transactional
    @SneakyThrows
    public void deleteHotel(Integer hotelId) {
        Hotel hotel = this.hotelService.findHotel(hotelId);
//        Integer accommodationId = this.accommodationPackageService.getAccommodationPackageId(hotelId);
        Integer destinationId = hotel.getDestination().getId();

        this.hotelDao.delete(hotelId);
        log.info("Hotel with id {} has been deleted!", hotelId);

        List<Hotel> destinations = this.hotelRepository.findByDestinationId(destinationId);
        log.info("Destinations {}", destinations);

        if (destinations.isEmpty()) {
            this.destinationDao.delete(hotel.getDestination().getId());
            log.info("Destination with id {} has been deleted!", hotel.getDestination().getId());
        }
    }


}
