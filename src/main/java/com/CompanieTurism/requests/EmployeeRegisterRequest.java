package com.CompanieTurism.requests;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class EmployeeRegisterRequest extends BaseEmployeeRequest {

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String password;
}

