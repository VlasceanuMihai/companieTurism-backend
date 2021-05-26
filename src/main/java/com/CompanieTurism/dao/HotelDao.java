package com.CompanieTurism.dao;

import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.HotelRepository;
import com.googlecode.jmapper.JMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public boolean countDuplicateHotel(Integer employeeId, String hotelName, String country, String city) {
        log.info("Employee id {}, hotelName {}, country {}, city {}.", employeeId, hotelName, country, city);
        int count = this.hotelRepository.countAllByEmployeeAndHotelAndDestination(employeeId, hotelName, country, city);
        log.info("Count {}", count);
        return count >= 1;
    }

    @Transactional
    public HotelDto save(Hotel hotel) {
        return TO_HOTEL_DTO.getDestination(this.hotelRepository.save(hotel));
    }
}
