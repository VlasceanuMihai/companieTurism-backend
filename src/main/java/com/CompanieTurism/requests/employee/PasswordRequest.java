package com.CompanieTurism.requests.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PasswordRequest {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    private String currentPassword;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    private String newPassword;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 70)
    private String confirmNewPassword;
}
