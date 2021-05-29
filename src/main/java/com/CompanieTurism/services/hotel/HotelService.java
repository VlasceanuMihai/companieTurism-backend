package com.CompanieTurism.services.hotel;

import com.CompanieTurism.exceptions.HotelNotFoundException;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
