package com.CompanieTurism.dao;

import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelDao {

    public static final JMapper<HotelDto, Hotel> TO_HOTEL_DTO = new JMapper<>(HotelDto.class, Hotel.class);
    public static final JMapper<Hotel, HotelDto> TO_HOTEL_ENTITY = new JMapper<>(Hotel.class, HotelDto.class);

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelDao(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
}
