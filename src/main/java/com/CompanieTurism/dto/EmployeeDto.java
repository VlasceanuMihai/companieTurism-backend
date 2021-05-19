package com.CompanieTurism.dto;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
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
    @NotBlank
    private LocalDate dateOfEmployment;

    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @NotNull
    @NotBlank
    private Integer wage;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    private Instant createdAt;

    @JsonIgnore
    public String getFullName() {
        return Stream.of(firstName, lastName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim()
                .replace(" +", "");
    }
}

