package com.CompanieTurism.repository;

import com.CompanieTurism.models.Flight;
import com.CompanieTurism.responses.document.DocumentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
