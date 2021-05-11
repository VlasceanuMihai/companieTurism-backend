package com.CompanieTurism.repository;

import com.CompanieTurism.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZborRepository extends JpaRepository<Flight, Integer> {
}
