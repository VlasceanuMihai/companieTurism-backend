package com.CompanieTurism.controllers.employees;

import com.CompanieTurism.requests.employee.BaseEmployeeRequest;
import com.CompanieTurism.requests.employee.EmployeeRegisterRequest;
import com.CompanieTurism.security.UserPrincipal;
import com.CompanieTurism.services.employee.EmployeeAdminService;
import com.CompanieTurism.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeAdminController {

    private final EmployeeAdminService employeeAdminService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeAdminController(EmployeeAdminService employeeAdminService, EmployeeService employeeService) {
        this.employeeAdminService = employeeAdminService;
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/profile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> adminEmployeeProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.employeeAdminService.getAdminEmployeeProfile(userPrincipal.getId()));
    }

    @GetMapping("/v1/employee/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getEmployees(@PathVariable Integer employeeId) {
        return ResponseEntity.ok(this.employeeAdminService.getEmployee(employeeId));
    }

    @GetMapping("/v1/employees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getEmployees(@PageableDefault(size = 2) Pageable pageable) {
        return ResponseEntity.ok(this.employeeAdminService.getEmployeesByPageable(pageable));
    }

    @GetMapping("/v1/getAllEmployees")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllEmployees() {
        return ResponseEntity.ok(this.employeeAdminService.getAllEmployees());
    }

    @PostMapping("/v1/createEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody EmployeeRegisterRequest employeeRequest) {
        return ResponseEntity.ok(this.employeeAdminService.createEmployee(employeeRequest));
    }

    @PutMapping("/v1/updateEmployee/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateEmployee(@PathVariable Integer employeeId,
                                                 @Valid @RequestBody BaseEmployeeRequest employeeRequest) {
        return ResponseEntity.ok(this.employeeAdminService.updateEmployee(employeeId, employeeRequest));
    }

    @DeleteMapping("/v1/deleteEmployee/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer employeeId) {
        this.employeeAdminService.deleteEmployee(employeeId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
