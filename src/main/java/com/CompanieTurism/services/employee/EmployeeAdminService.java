package com.CompanieTurism.services.employee;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.enums.Role;
import com.CompanieTurism.exceptions.EmployeeExistsException;
import com.CompanieTurism.exceptions.EmployeeNotFoundException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import com.CompanieTurism.requests.employee.BaseEmployeeRequest;
import com.CompanieTurism.requests.employee.EmployeeRegisterRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeAdminService {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeAdminService(EmployeeService employeeService,
                                EmployeeRepository employeeRepository,
                                PasswordEncoder passwordEncoder, EmployeeDao employeeDao) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeDao = employeeDao;
    }

    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        return this.employeeRepository.findAll(pageable).stream()
                .map(EmployeeDao.TO_EMPLOYEE_DTO::getDestination)
                .collect(Collectors.toList());
    }

    @Transactional
    @SneakyThrows
    public EmployeeDto createEmployee(EmployeeRegisterRequest employeeRequest) {
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

    private Employee getUpdatedEmployee(EmployeeRegisterRequest employeeRequest) {
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

    @Transactional
    @SneakyThrows
    public EmployeeDto updateEmployee(Integer employeeId, BaseEmployeeRequest employeeRequest) {
        if (!this.employeeService.checkExistingId(employeeId)) {
            log.info("Employee with id {} not found.", employeeId);
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found!");
        }

        int res = this.employeeRepository.updateEmployee(employeeRequest.getLastName(),
                employeeRequest.getFirstName(),
                employeeRequest.getCnp(),
                employeeRequest.getPhoneNumber(),
                employeeRequest.getEmail(),
                employeeRequest.getDateOfEmployment(),
                employeeRequest.getEmployeeType(),
                employeeRequest.getWage(),
                employeeId);

        if (res < 1) {
            throw new PersistenceException("Cannot update employee with employeeId: " + employeeId);
        }

        log.info("Employee with id {} has been updated with payload {}", employeeId, employeeRequest);
        return EmployeeDto.builder()
                .id(employeeId)
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .cnp(employeeRequest.getCnp())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .email(employeeRequest.getEmail())
                .dateOfEmployment(employeeRequest.getDateOfEmployment())
                .employeeType(employeeRequest.getEmployeeType())
                .wage(employeeRequest.getWage())
                .role(Role.ROLE_USER)
                .build();

    }

    @Transactional
    @SneakyThrows
    public void deleteEmployee(Integer employeeId) {
        if (!this.employeeService.checkExistingId(employeeId)) {
            log.info("Employee with id {} not found.", employeeId);
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " not found!");
        }

        this.employeeDao.delete(employeeId);
        log.info("Employee with id {} has been deleted!", employeeId);
    }
}
