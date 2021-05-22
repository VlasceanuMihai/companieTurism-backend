package com.CompanieTurism.repository;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByCnp(String cnp);

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByPhoneNumber(String phoneNumber);

    Boolean existsByCnp(String cnp);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Employee " +
            "SET lastName = :lastName, firstName = :firstName, cnp = :cnp," +
            " phoneNumber = :phoneNumber, email = :email, dateOfEmployment = :dateOfEmployment," +
            " employeeType = :employeeType, wage = :wage " +
            "WHERE id = :employeeId")
    int updateEmployee(@Param("lastName") String lastName,
                       @Param("firstName") String firstName,
                       @Param("cnp") String cnp,
                       @Param("phoneNumber") String phoneNumber,
                       @Param("email") String email,
                       @Param("dateOfEmployment") LocalDate dateOfEmployment,
                       @Param("employeeType") EmployeeType employeeType,
                       @Param("wage") Integer wage,
                       @Param("employeeId") Integer employeeId);
}
