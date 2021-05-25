package com.CompanieTurism.repository;

import com.CompanieTurism.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
