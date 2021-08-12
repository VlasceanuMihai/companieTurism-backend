package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.DestinationDao;
import com.CompanieTurism.exceptions.HotelNotFoundException;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.CompanieTurism.responses.hotel.HotelResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public boolean checkExistingId(Integer hotelId) {
        return this.hotelRepository.existsById(hotelId);
    }

    public Hotel findHotel(Integer hotelId) {
        return this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id: " + hotelId + " not found!"));
    }

    public List<HotelResponse> getHotelsAndDestinations() {
        List<Hotel> hotels = this.hotelRepository.findHotels();

        List<HotelResponse> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            HotelResponse newHotel = HotelResponse.builder()
                    .id(hotel.getId())
                    .hotelName(hotel.getName())
                    .hotelRating(hotel.getRating())
                    .destination(DestinationDao.TO_DESTINATION_DTO.getDestination(hotel.getDestination()))
                    .build();
            response.add(newHotel);
        }
        return response;
    }
}
