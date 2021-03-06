package com.CompanieTurism.dao;

import com.CompanieTurism.dto.EmployeeDto;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmployeeDao {

    public static final JMapper<EmployeeDto, Employee> TO_EMPLOYEE_DTO = new JMapper<>(EmployeeDto.class, Employee.class);
    public static final JMapper<Employee, EmployeeDto> TO_EMPLOYEE_ENTITY = new JMapper<>(Employee.class, EmployeeDto.class);

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDao(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeDto> findById(Integer id) {
        return this.employeeRepository.findById(id).map(TO_EMPLOYEE_DTO::getDestination);
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeDto> findByCnp(String cnp) {
        return this.employeeRepository.findByCnp(cnp).map(TO_EMPLOYEE_DTO::getDestination);
    }

    @Transactional
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = this.employeeRepository.save(TO_EMPLOYEE_ENTITY.getDestination(employeeDto));
        return TO_EMPLOYEE_DTO.getDestination(employee);
    }

    @Transactional
    public void delete(Integer empoyeeId) {
        this.employeeRepository.deleteById(empoyeeId);
    }
}

