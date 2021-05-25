package com.CompanieTurism.responses.employee;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeProfileResponse {

    private String lastName;

    private String firstName;

    private String cnp;

    private String phoneNumber;

    private String email;

    private LocalDate dateOfEmployment;

    private EmployeeType employeeType;

    private Integer wage;

    private Role role;

    private Instant createdAt;
}
