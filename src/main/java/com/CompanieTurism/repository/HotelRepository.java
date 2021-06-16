package com.CompanieTurism.repository;

import com.CompanieTurism.enums.CovidScenario;
import com.CompanieTurism.models.Destination;
import com.CompanieTurism.models.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    boolean existsByNameAndDestination(String name, Destination destination);

    boolean existsByNameAndDestinationCountryAndDestinationCity(@Param("hotelName") String hotelName,
                                                                @Param("country") String country,
                                                                @Param("city") String city);

    Optional<Hotel> findByNameAndDestination(String name, Destination destination);

    @Query(value = "SELECT * FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee", nativeQuery = true)
    List<Hotel> findHotelsByPageable(Pageable pageable);

    @Query(value = "SELECT * FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee", nativeQuery = true)
    List<Hotel> findHotels();

    @Query(value = "SELECT COUNT(*) FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "WHERE h.name = :hotelName AND d.country = :country AND d.city = :city", nativeQuery = true)
    int countAllByEmployeeAndHotelAndDestination(@Param("hotelName") String hotelName,
                                                 @Param("country") String country,
                                                 @Param("city") String city);

    @Query(value = "SELECT COUNT(*) FROM hotels h " +
            "WHERE h.id_destination = :destinationId", nativeQuery = true)
    int countByDestinationId(Integer destinationId);

    @Query(value = "SELECT h FROM Hotel h WHERE h.destination.id IN (:destinationId)")
    List<Hotel> findByDestinationId(Integer destinationId);

    @Query(value = "SELECT * FROM hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "JOIN employees e ON e.id = d.id_employee " +
            "WHERE e.id = :employeeId", nativeQuery = true)
    List<Hotel> findAllByEmployeeId(@Param("employeeId") Integer employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE hotels h " +
            "JOIN destinations d ON d.id = h.id_destination " +
            "SET name = :name, rating = :rating, " +
            "d.country = :country, d.city = :city, d.covid_scenario = :covidScenario " +
            "WHERE h.id = :hotelId", nativeQuery = true)
    int updateHotel(@Param("hotelId") Integer hotelId,
                    @Param("name") String name,
                    @Param("rating") Integer rating,
                    @Param("country") String country,
                    @Param("city") String city,
                    @Param("covidScenario") String covidScenario);

//    value = "UPDATE Hotel " +
//            "SET name = :name, rating = :rating, " +
//            "destination.country = :country, destination.city = :city, destination.covidScenario = :covidScenario " +
//            "WHERE id = :hotelId"
}
