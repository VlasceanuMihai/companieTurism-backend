package com.CompanieTurism.controllers.employees;

import com.CompanieTurism.requests.employee.PasswordRequest;
import com.CompanieTurism.security.UserPrincipal;
import com.CompanieTurism.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/v1/updatePassword")
    public ResponseEntity<Object> updatePassword(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                 @Valid @RequestBody PasswordRequest passwordRequest) {
        this.employeeService.updatePassword(userPrincipal.getId(), passwordRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
