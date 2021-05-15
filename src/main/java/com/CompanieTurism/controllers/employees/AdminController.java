package com.CompanieTurism.controllers.employees;

import com.CompanieTurism.requests.BaseEmployeeRequest;
import com.CompanieTurism.services.AdminService;
import com.CompanieTurism.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/employee")
public class AdminController {

    private final AdminService adminService;
    private final EmployeeService employeeService;

    @Autowired
    public AdminController(AdminService adminService, EmployeeService employeeService) {
        this.adminService = adminService;
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllEmployees(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.employeeService.getAllEmployees(pageable));
    }

    @PostMapping("/v1/createEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody BaseEmployeeRequest employeeRequest) {
        return ResponseEntity.ok(this.adminService.createEmployee(employeeRequest));
    }
}
