package com.CompanieTurism.requests.employee;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class PasswordRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 70)
    private String currentPassword;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 70)
    private String newPassword;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 70)
    private String confirmNewPassword;
}
