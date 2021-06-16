package com.CompanieTurism.dao;

import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.googlecode.jmapper.JMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class HotelDao {

    public static final JMapper<HotelDto, Hotel> TO_HOTEL_DTO = new JMapper<>(HotelDto.class, Hotel.class);
    public static final JMapper<Hotel, HotelDto> TO_HOTEL_ENTITY = new JMapper<>(Hotel.class, HotelDto.class);

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelDao(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public HotelDto save(Hotel hotel) {
        return TO_HOTEL_DTO.getDestination(this.hotelRepository.save(hotel));
    }

    @Transactional
    public void delete(Integer hotelId) {
        this.hotelRepository.deleteById(hotelId);
    }

    @Transactional
    public void deleteAll(List<Hotel> hotels) {
        this.hotelRepository.deleteAll(hotels);
    }
}
