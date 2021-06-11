package com.CompanieTurism.repository;

import com.CompanieTurism.models.Destination;
import com.CompanieTurism.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {

    boolean existsByCountryAndCity(String country, String city);

    boolean existsByEmployeeAndCountryAndCity(Employee employee, String country, String city);

    Destination findByEmployeeAndCountryAndCity(Employee employee, String country, String city);

    @Query(value = "SELECT * FROM destinations d " +
            "JOIN employees e ON e.id = d.id_employee " +
            "WHERE e.id = :employeeId", nativeQuery = true)
    List<Destination> findAllByEmployeeId(@Param("employeeId") Integer employeeId);
}
