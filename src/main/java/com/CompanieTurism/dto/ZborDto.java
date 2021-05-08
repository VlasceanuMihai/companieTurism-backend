package com.CompanieTurism.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class ZborDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String aeroportPlecare;

    @NotNull
    @NotBlank
    @FutureOrPresent
    private Instant dataOraPlecare;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String aeroportSosire;

    @NotNull
    @NotBlank
    @FutureOrPresent
    private Instant dataOraSosire;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String companie;
}
