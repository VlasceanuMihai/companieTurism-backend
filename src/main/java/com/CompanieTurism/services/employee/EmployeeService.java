package com.CompanieTurism.services.employee;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.exceptions.EmployeeNotFoundException;
import com.CompanieTurism.exceptions.ErrorMessage;
import com.CompanieTurism.exceptions.InvalidPasswordException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import com.CompanieTurism.requests.employee.PasswordRequest;
import com.CompanieTurism.responses.employee.EmployeeProfileResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDao employeeDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeDao employeeDao,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.employeeDao = employeeDao;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkExistingId(Integer employeeId) {
        return this.employeeRepository.existsById(employeeId);
    }

    public boolean checkExistingEmail(String email) {
        return this.employeeRepository.existsByEmail(email);
    }

    public boolean checkExistingPhoneNumber(String phoneNumber) {
        return this.employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean checkExistingCnp(String cnp) {
        return this.employeeRepository.existsByCnp(cnp);
    }

    public Employee findEmployeeByCnp(String cnp) {
        return this.employeeRepository.findByCnp(cnp)
                .orElseThrow(() -> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));
    }

    public EmployeeProfileResponse getEmployeeProfile(Integer employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));

        return EmployeeProfileResponse.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .cnp(employee.getCnp())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .dateOfEmployment(employee.getDateOfEmployment())
                .employeeType(employee.getEmployeeType())
                .wage(employee.getWage())
                .role(employee.getRole())
                .createdAt(employee.getCreatedAt())
                .build();
    }

    public List<EmployeeDto> getEmployeesByPageable(Pageable pageable) {
        return this.employeeRepository.findAll(pageable).stream()
                .map(EmployeeDao.TO_EMPLOYEE_DTO::getDestination)
                .collect(Collectors.toList());
    }

    public List<EmployeeDto> getAllEmployees() {
        return this.employeeRepository.findAll().stream()
                .map(EmployeeDao.TO_EMPLOYEE_DTO::getDestination)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updatePassword(Integer employeeId, PasswordRequest passwordRequest) {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));

        String currentEncodedPassword = employee.getPassword();
        String currentPassword = passwordRequest.getCurrentPassword();
        if (!this.passwordEncoder.matches(currentPassword, currentEncodedPassword)) {
            throw new InvalidPasswordException("Invalid current password for employee with id: " + employeeId);
        }

        if (!passwordRequest.getNewPassword().equalsIgnoreCase(passwordRequest.getConfirmNewPassword())) {
            throw new InvalidPasswordException("New password doesn't match for employee with id: " + employeeId);
        } else if (currentPassword.equalsIgnoreCase(passwordRequest.getNewPassword())) {
            throw new InvalidPasswordException("New password doesn't have to be the same as the current password for employee with id: " + employeeId);
        }

        employee.setPassword(this.passwordEncoder.encode(passwordRequest.getNewPassword()));
        this.employeeRepository.save(employee);
        log.info("Password has been changed for employee id: {}", employeeId);
    }
}
