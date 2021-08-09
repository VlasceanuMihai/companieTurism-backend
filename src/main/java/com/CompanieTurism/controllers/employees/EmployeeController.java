package com.CompanieTurism.controllers.employees;

import com.CompanieTurism.requests.employee.PasswordRequest;
import com.CompanieTurism.security.UserPrincipal;
import com.CompanieTurism.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/v1/profile")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> employeeProfile(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.employeeService.getEmployeeProfile(userPrincipal.getId()));
    }

//    @GetMapping("/v1/employees")
//    public ResponseEntity<Object> getEmployees(@PageableDefault(size = 2) Pageable pageable) {
//        return ResponseEntity.ok(this.employeeService.getEmployeesByPageable(pageable));
//    }

    @GetMapping("/v1/employees")
    public ResponseEntity<Object> getEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    @PutMapping("/v1/updatePassword")
    public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @Valid @RequestBody PasswordRequest passwordRequest) {
        this.employeeService.updatePassword(userPrincipal.getId(), passwordRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
