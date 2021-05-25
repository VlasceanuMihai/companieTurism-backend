package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dao.HotelDao;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.CompanieTurism.responses.hotel.HotelAndDestinationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HotelService {

    private final HotelDao hotelDao;
    private final HotelRepository hotelRepository;
    private final EmployeeDao employeeDao;

    @Autowired
    public HotelService(HotelDao hotelDao,
                        HotelRepository hotelRepository,
                        EmployeeDao employeeDao) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.employeeDao = employeeDao;
    }

    public List<HotelAndDestinationResponse> getHotelsAndDestinations(Pageable pageable) {
        List<Hotel> hotels = this.hotelRepository.findHotels(pageable);

        List<HotelAndDestinationResponse> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelAndDestinationResponse newHotel = HotelAndDestinationResponse.builder()
                    .hotelId(hotel.getId())
                    .hotelName(hotel.getName())
                    .hotelRating(hotel.getRating())
                    .country(hotel.getDestination().getCountry())
                    .city(hotel.getDestination().getCity())
                    .covidScenario(hotel.getDestination().getCovidScenario())
                    .employeeDto(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(hotel.getDestination().getEmployee()))
                    .build();
            response.add(newHotel);
        }
        return response;
    }
}
