package com.CompanieTurism.requests;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BaseEmployeeRequest {

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 13)
    private String cnp;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    private LocalDate dateOfEmployment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @NotNull
    private Integer wage;

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String password;
}
