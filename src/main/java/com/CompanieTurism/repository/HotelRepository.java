package com.CompanieTurism.repository;

import com.CompanieTurism.models.Destination;
import com.CompanieTurism.models.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    boolean existsByNameAndDestination(String name, Destination destination);

    Optional<Hotel> findByNameAndDestination(String name, Destination destination);

    @Query(value = "SELECT * FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee", nativeQuery = true)
    List<Hotel> findHotels(Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee " +
            "WHERE e.id = :employeeId AND h.name = :hotelName " +
            "AND d.country = :country AND d.city = :city", nativeQuery = true)
    int countAllByEmployeeAndHotelAndDestination(@Param("employeeId") Integer employeeId,
                                                 @Param("hotelName") String hotelName,
                                                 @Param("country") String country,
                                                 @Param("city") String city);

    /*
    SELECT hotels.id,hotels.name,hotels.rating,destinations.country,destinations.city,destinations.covid_scenario,employees.last_name,employees.first_name
    FROM hotels
    JOIN destinations
    ON destinations.id =hotels.id_destination
    JOIN employees
    ON employees.id =destinations.id_employee;

    new com.CompanieTurism.responses.hotel.HotelAndDestinationResponse(" +
            "h.id, h.name, h.rating, d.country, d.city, d.covidScenario, d.employee)
     */

}
