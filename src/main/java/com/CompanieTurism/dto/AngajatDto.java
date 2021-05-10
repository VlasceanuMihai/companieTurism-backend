package com.CompanieTurism.dto;

import com.CompanieTurism.enums.TipAngajat;
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
public class AngajatDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String nume;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String prenume;

    @NotNull
    @NotBlank
    @Size(max = 13)
    private String cnp;

    @NotNull
    @NotBlank
    @Size(max = 10)
    private String telefon;

    @NotNull
    @NotBlank
    @Email
    @Size(max = 70)
    private String email;

    @NotNull
    @NotBlank
    private LocalDate dataAngajare;

    @NotNull
    @NotBlank
    private TipAngajat tipAngajat;

    @NotNull
    @NotBlank
    @Max(5)
    private Integer salariu;

    public String getFullName() {
        return Stream.of(prenume, nume)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "))
                .trim()
                .replace(" +", "");
    }
}

