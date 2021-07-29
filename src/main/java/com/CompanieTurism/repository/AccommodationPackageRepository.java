package com.CompanieTurism.repository;

import com.CompanieTurism.enums.PackageType;
import com.CompanieTurism.models.AccommodationPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccommodationPackageRepository extends JpaRepository<AccommodationPackage, Integer> {

    List<AccommodationPackage> findByHotelId(Integer hotelId);

    @Query(value = "SELECT * FROM accommodation_packages ap " +
            "JOIN hotels h ON h.id = ap.id_hotel " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee " +
            "WHERE e.id = :employeeId", nativeQuery = true)
    List<AccommodationPackage> findAllByEmployeeId(@Param("employeeId") Integer employeeId);

    List<AccommodationPackage> findAllByHotelId(@Param("hotelId") Integer hotelId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE AccommodationPackage " +
            "SET packageType = :packageType, pricePerNight = :pricePerNight, nightsNumber = :nightsNumber, " +
            "roomsNumber = :roomsNumber, adultsNumber = :adultsNumber, kidsNumber = :kidsNumber, totalPrice = :totalPrice " +
            "WHERE id = :accommodationPackageId")
    int updateById(@Param("accommodationPackageId") Integer accommodationPackageId,
                   @Param("packageType") PackageType packageType,
                   @Param("pricePerNight") Integer pricePerNight,
                   @Param("nightsNumber") Integer nightsNumber,
                   @Param("roomsNumber") Integer roomsNumber,
                   @Param("adultsNumber") Integer adultsNumber,
                   @Param("kidsNumber") Integer kidsNumber,
                   @Param("totalPrice") Integer totalPrice);
}
