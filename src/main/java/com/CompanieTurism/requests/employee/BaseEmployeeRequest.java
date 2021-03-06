package com.CompanieTurism.requests.employee;

import com.CompanieTurism.enums.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
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
}
