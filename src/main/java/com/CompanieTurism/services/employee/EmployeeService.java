package com.CompanieTurism.services.employee;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.exceptions.EmployeeNotFoundException;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeDao employeeDao) {
        this.employeeRepository = employeeRepository;
        this.employeeDao = employeeDao;
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

    public Employee findEmployeeByCnp(String cnp){
        return this.employeeRepository.findByCnp(cnp)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with cnp " + cnp + " not found!"));
    }
}
