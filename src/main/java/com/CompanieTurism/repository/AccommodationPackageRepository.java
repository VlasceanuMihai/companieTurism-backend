package com.CompanieTurism.repository;

import com.CompanieTurism.models.AccommodationPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationPackageRepository extends JpaRepository<AccommodationPackage, Integer> {

    List<AccommodationPackage> findByHotelId(Integer hotelId);
}
