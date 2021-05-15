package com.CompanieTurism.services;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.enums.Role;
import com.CompanieTurism.exceptions.EmployeeExistsException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import com.CompanieTurism.requests.BaseEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
public class AdminService {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(EmployeeService employeeService,
                        EmployeeRepository employeeRepository,
                        PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public EmployeeDto createEmployee(BaseEmployeeRequest employeeRequest) {
        if (employeeService.checkExistingEmail(employeeRequest.getEmail())
                || employeeService.checkExistingPhoneNumber(employeeRequest.getPhoneNumber())
                || employeeService.checkExistingCnp(employeeRequest.getCnp())) {
            log.info("Employee with email {}, phone number {}, cnp {} already exists!", employeeRequest.getEmail(),
                    employeeRequest.getPhoneNumber(),
                    employeeRequest.getCnp());
            throw new EmployeeExistsException("Employee with email - " + employeeRequest.getEmail() +
                    ", phone number - " + employeeRequest.getPhoneNumber() +
                    ", cnp - " + employeeRequest.getCnp() +
                    "already exists!");
        }

        Employee employee = this.employeeRepository.save(this.getUpdatedEmployee(employeeRequest));
        return EmployeeDao.TO_EMPLOYEE_DTO.getDestination(employee);
    }

    private Employee getUpdatedEmployee(BaseEmployeeRequest employeeRequest) {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setLastName(employeeRequest.getLastName());
        updatedEmployee.setFirstName(employeeRequest.getFirstName());
        updatedEmployee.setCnp(employeeRequest.getCnp());
        updatedEmployee.setPhoneNumber(employeeRequest.getPhoneNumber());
        updatedEmployee.setEmail(employeeRequest.getEmail());
        updatedEmployee.setDateOfEmployment(employeeRequest.getDateOfEmployment());
        updatedEmployee.setEmployeeType(employeeRequest.getEmployeeType());
        updatedEmployee.setWage(employeeRequest.getWage());
        updatedEmployee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        updatedEmployee.setRole(Role.ROLE_USER);
        updatedEmployee.setCreatedAt(Instant.now());

        return updatedEmployee;
    }
}
