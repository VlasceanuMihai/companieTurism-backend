package com.CompanieTurism.controllers;

import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.EmployeeRepository;
import com.CompanieTurism.requests.AdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class AddAdminController {

    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AddAdminController(PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/addAdminOrManager")
    @Transactional
    public ResponseEntity<Object> addAdminOrManager(@Valid @RequestBody AdminRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setCnp(request.getCnp());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setEmail(request.getEmail());
        employee.setDateOfEmployment(request.getDateOfEmployment());
        employee.setEmployeeType(request.getEmployeeType());
        employee.setWage(request.getWage());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(request.getRole());
        employee.setCreatedAt(request.getCreatedAt());

        employeeRepository.save(employee);

        return ResponseEntity.ok(employee);
    }
}
