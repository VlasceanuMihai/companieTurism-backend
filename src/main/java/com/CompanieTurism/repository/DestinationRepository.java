package com.CompanieTurism.repository;

import com.CompanieTurism.models.Destination;
import com.CompanieTurism.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    boolean existsByCountryAndCity(String country, String city);

    boolean existsByEmployeeAndCountryAndCity(Employee employee, String country, String city);

    Destination findByEmployeeAndCountryAndCity(Employee employee, String country, String city);
}
