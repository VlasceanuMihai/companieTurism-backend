package com.CompanieTurism.services.hotel;

import com.CompanieTurism.models.AccommodationPackage;
import com.CompanieTurism.repository.AccommodationPackageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccommodationPackageService {

    private final AccommodationPackageRepository accommodationPackageRepository;

    @Autowired
    public AccommodationPackageService(AccommodationPackageRepository accommodationPackageRepository) {
        this.accommodationPackageRepository = accommodationPackageRepository;
    }

    public List<AccommodationPackage> getAccommodationPackages(Integer hotelId) {
        return this.accommodationPackageRepository.findByHotelId(hotelId);
    }

    //    public Integer getAccommodationPackageId(Integer hotelId) {
//        AccommodationPackage accommodationPackage = this.accommodationPackageRepository.findByHotelId(hotelId)
//                .orElseThrow(() -> new AccommodationPackageNotFoundException("Accommodation package for hotel id: " + hotelId + " not found!"));
//
//        return accommodationPackage.getId();
//    }
}
