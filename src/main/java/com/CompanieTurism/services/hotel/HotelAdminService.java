package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.DestinationDao;
import com.CompanieTurism.dao.HotelDao;
import com.CompanieTurism.dto.DestinationDto;
import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.exceptions.ErrorMessage;
import com.CompanieTurism.exceptions.HotelExistsException;
import com.CompanieTurism.exceptions.HotelNotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
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
    private final AccommodationPackageAdminService accommodationAdminService;
    private final AccommodationPackageService accommodationService;

    @Autowired
    public HotelAdminService(HotelDao hotelDao,
                             HotelRepository hotelRepository,
                             HotelService hotelService,
                             EmployeeService employeeService,
                             DestinationDao destinationDao,
                             DestinationRepository destinationRepository,
                             AccommodationPackageAdminService accommodationAdminService,
                             AccommodationPackageService accommodationService) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
        this.employeeService = employeeService;
        this.destinationDao = destinationDao;
        this.destinationRepository = destinationRepository;
        this.accommodationAdminService = accommodationAdminService;
        this.accommodationService = accommodationService;
    }

    public HotelDto getHotel(Integer hotelId) {
        Hotel hotel = this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(ErrorMessage.HOTEL_NOT_FOUND));
        log.info("Hotel request: {}", hotel);

        return HotelDao.TO_HOTEL_DTO.getDestination(hotel);
    }

    @Transactional
    @SneakyThrows
    public HotelResponse createHotelWithDestination(HotelRegisterRequest hotelRequest) {
        Employee employee = this.employeeService.findEmployeeByCnp(hotelRequest.getCnp());

        String hotelName = hotelRequest.getHotelName();
        String country = hotelRequest.getDestination().getCountry();
        String city = hotelRequest.getDestination().getCity();

        boolean checkExistingHotel = this.hotelRepository.existsByNameAndDestinationCountryAndDestinationCity(
                hotelName, country, city);

        if (checkExistingHotel) {
            log.info(" Hotel: {}, country: {}, city: {} is already assigned to an employee id: {}",
                    hotelName, country, city, employee.getId());
            throw new HotelExistsException(ErrorMessage.HOTEL_ALREADY_EXISTS);
        }

        Optional<Destination> optionalDestination = this.destinationRepository.findByEmployeeAndCountryAndCity(employee, country, city);

        Destination destination;
        DestinationDto savedDestinationDto;
        if (optionalDestination.isPresent()) {
            destination = optionalDestination.get();
            savedDestinationDto = DestinationDao.TO_DESTINATION_DTO.getDestination(destination);
            log.info("Destination {} already exists!", savedDestinationDto);
        } else {
            destination = Destination.builder()
                    .employee(employee)
                    .country(country)
                    .city(city)
                    .covidScenario(hotelRequest.getDestination().getCovidScenario())
                    .build();

            savedDestinationDto = this.destinationDao.save(destination);
            log.info("Destination with country: {} and city: {} is saved!", country, city);
        }

        Optional<Hotel> optionalHotel = this.hotelRepository.findByNameAndDestination(hotelName, destination);
        Hotel hotel;
        if (optionalHotel.isPresent()) {
            log.info("Hotel with name: {} and destination: {} is already saved for employee id: {}!",
                    hotelName, savedDestinationDto, employee.getId());
            throw new HotelExistsException(ErrorMessage.HOTEL_ALREADY_EXISTS);
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
                .id(hotel.getId())
                .hotelName(hotelName)
                .hotelRating(hotel.getRating())
                .destination(savedDestinationDto)
                .build();
    }

    @Transactional
    @SneakyThrows
    public HotelDto updateHotel(Integer hotelId, HotelRegisterRequest hotelRequest) {
        log.info("Request: {}", hotelRequest);
        if (!this.hotelService.checkExistingId(hotelId)) {
            log.info(" Hotel id: {} not found.", hotelId);
            throw new HotelNotFoundException(ErrorMessage.HOTEL_NOT_FOUND);
        }

        log.info("Covid: {}", hotelRequest.getDestination().getCovidScenario());

        int res = this.hotelRepository.updateHotel(
                hotelId,
                hotelRequest.getHotelName(),
                hotelRequest.getRating(),
                hotelRequest.getDestination().getCountry(),
                hotelRequest.getDestination().getCity(),
                hotelRequest.getDestination().getCovidScenario().name());

        if (res < 1) {
            log.info("Cannot update hotel with id: {}", hotelId);
            throw new PersistenceException("Cannot update hotel with id: " + hotelId);
        }
        log.info("Hotel with id {} has been updated with payload {}", hotelId, hotelRequest);

        Hotel hotel = this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(ErrorMessage.HOTEL_NOT_FOUND));

        return HotelDao.TO_HOTEL_DTO.getDestination(hotel);
    }

    @Transactional
    @SneakyThrows
    public void deleteHotel(Integer hotelId) {
        Hotel hotel = this.hotelService.findHotel(hotelId);
        Integer destinationId = hotel.getDestination().getId();

        this.accommodationAdminService.deleteAccommodationBasedOnHotelId(hotelId);

        this.hotelDao.delete(hotelId);
        log.info("Hotel with id {} has been deleted!", hotelId);

        List<Hotel> destinations = this.hotelRepository.findByDestinationId(destinationId);
        log.info("Destinations {}", destinations);

        if (destinations.isEmpty()) {
            this.destinationDao.delete(hotel.getDestination().getId());
            log.info("Destination with id {} has been deleted!", hotel.getDestination().getId());
        }
    }

    @Transactional
    @SneakyThrows
    public void deleteHotelBasedOnEmployeeId(Integer employeeId) {
        List<Hotel> hotels = this.hotelRepository.findAllByEmployeeId(employeeId);
        if (hotels.isEmpty()) {
            log.info("No hotels for employee id {}", employeeId);
            return;
        }

        this.hotelDao.deleteAll(hotels);
    }

}
