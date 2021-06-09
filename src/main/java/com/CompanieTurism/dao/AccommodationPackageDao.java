package com.CompanieTurism.dao;

import com.CompanieTurism.dto.AccommodationPackageDto;
import com.CompanieTurism.models.AccommodationPackage;
import com.CompanieTurism.repository.AccommodationPackageRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccommodationPackageDao {

    public static final JMapper<AccommodationPackageDto, AccommodationPackage> TO_ACCOMMODATION_DTO = new JMapper<>(AccommodationPackageDto.class, AccommodationPackage.class);
    public static final JMapper<AccommodationPackage, AccommodationPackageDto> TO_ACCOMMODATION_ENTITY = new JMapper<>(AccommodationPackage.class, AccommodationPackageDto.class);

    private final AccommodationPackageRepository accommodationPackageRepository;

    @Autowired
    public AccommodationPackageDao(AccommodationPackageRepository accommodationPackageRepository) {
        this.accommodationPackageRepository = accommodationPackageRepository;
    }

    @Transactional
    public void deleteAll(List<AccommodationPackage> accommodationPackages){
        this.accommodationPackageRepository.deleteAll(accommodationPackages);
    }
}
