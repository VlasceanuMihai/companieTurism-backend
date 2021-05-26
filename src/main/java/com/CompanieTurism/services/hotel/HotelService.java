package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.DestinationDao;
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
    private final DestinationDao destinationDao;

    @Autowired
    public HotelService(HotelDao hotelDao,
                        HotelRepository hotelRepository,
                        EmployeeDao employeeDao,
                        DestinationDao destinationDao) {
        this.hotelDao = hotelDao;
        this.hotelRepository = hotelRepository;
        this.employeeDao = employeeDao;
        this.destinationDao = destinationDao;
    }

    public List<HotelAndDestinationResponse> getHotelsAndDestinations(Pageable pageable) {
        List<Hotel> hotels = this.hotelRepository.findHotels(pageable);

        List<HotelAndDestinationResponse> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelAndDestinationResponse newHotel = HotelAndDestinationResponse.builder()
                    .hotelId(hotel.getId())
                    .hotelName(hotel.getName())
                    .hotelRating(hotel.getRating())
                    .destination(DestinationDao.TO_DESTINATION_DTO.getDestination(hotel.getDestination()))
                    .employee(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(hotel.getDestination().getEmployee()))
                    .build();
            response.add(newHotel);
        }
        return response;
    }
}
