package com.CompanieTurism.repository;

import com.CompanieTurism.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    boolean existsByCountryAndCity(String country, String city);

    Destination findByCountryAndCity(String country, String city);
}
