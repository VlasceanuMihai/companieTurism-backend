package com.CompanieTurism.repository;

import com.CompanieTurism.models.Document;
import com.CompanieTurism.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findAllByEmployeeId(@Param("employeeId") Integer employeeId);

    @Query(value = "SELECT f FROM Flight f " +
            "JOIN Employee e ON e.id = f.employee.id " +
            "WHERE f.id = :flightId")
    Optional<Flight> findByIdAndEmployee(@Param("flightId") Integer flightId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Flight " +
            "SET airportDeparture = :airportDeparture, dateOfDeparture = :dateOfDeparture," +
            " airportArrival = :airportArrival, dateOfArrival = :dateOfArrival, company = :company " +
            "WHERE id = :flightId")
    int updateFlight(@Param("flightId") Integer flightId,
                     @Param("airportDeparture") String airportDeparture,
                     @Param("dateOfDeparture") LocalDateTime dateOfDeparture,
                     @Param("airportArrival") String airportArrival,
                     @Param("dateOfArrival") LocalDateTime dateOfArrival,
                     @Param("company") String company);
}
