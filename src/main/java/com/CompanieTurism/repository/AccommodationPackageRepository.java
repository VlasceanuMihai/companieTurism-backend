package com.CompanieTurism.repository;

import com.CompanieTurism.models.AccommodationPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
