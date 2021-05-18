package com.CompanieTurism.controllers.employees;

import com.CompanieTurism.requests.BaseEmployeeRequest;
import com.CompanieTurism.requests.EmployeeRegisterRequest;
import com.CompanieTurism.services.EmployeeAdminService;
import com.CompanieTurism.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeAdminController {

    private final EmployeeAdminService employeeAdminService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeAdminController(EmployeeAdminService employeeAdminService, EmployeeService employeeService) {
        this.employeeAdminService = employeeAdminService;
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllEmployees(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.employeeService.getAllEmployees(pageable));
    }

//    @GetMapping("/v1/employee/{employeeId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity<Object> employeeProfile(@PathVariable Integer employeeId) {
//
//    }

    @PostMapping("/v1/createEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRegisterRequest employeeRequest) {
        return ResponseEntity.ok(this.employeeAdminService.createEmployee(employeeRequest));
    }

    @PutMapping("/v1/updateEmployee/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateEmployee(@PathVariable Integer employeeId,
                                                 @Valid @RequestBody BaseEmployeeRequest employeeRequest) {
        this.employeeAdminService.updateEmployee(employeeId, employeeRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
