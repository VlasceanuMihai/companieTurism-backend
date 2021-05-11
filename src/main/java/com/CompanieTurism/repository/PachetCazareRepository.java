package com.CompanieTurism.repository;

import com.CompanieTurism.models.AccommodationPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PachetCazareRepository extends JpaRepository<AccommodationPackage, Integer> {
}
