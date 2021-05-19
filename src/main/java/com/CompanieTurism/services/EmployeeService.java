package com.CompanieTurism.services;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        return this.employeeRepository.findAll(pageable).stream()
                .map(EmployeeDao.TO_EMPLOYEE_DTO::getDestination)
                .collect(Collectors.toList());
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
}
