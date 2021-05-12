package com.CompanieTurism.dto;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.enums.Role;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"cnp"})
@JGlobalMap
public class EmployeeDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 40)
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
    @Email
    @Size(max = 70)
    private String email;

    @NotNull
    @NotBlank
    private LocalDate dateOfEmployment;

    @NotNull
    @NotBlank
    private EmployeeType employeeType;

    @NotNull
    @NotBlank
    @Max(5)
    private Integer wage;

    @NotNull
    private Role role;

    public String getFullName() {
        return Stream.of(firstName, lastName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim()
                .replace(" +", "");
    }
}

