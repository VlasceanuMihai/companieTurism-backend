package com.CompanieTurism.dto;

import com.CompanieTurism.enums.TipPachet;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class PachetCazareDto {

    private Integer id;

    private TipPachet tipPachet;

    @NotNull
    @NotBlank
    private Integer pretPeNoapte;

    @NotNull
    @NotBlank
    private Integer numarNopti;

    @NotNull
    @NotBlank
    private Integer numarCamere;

    @NotNull
    @NotBlank
    private Integer numarAdulti;

    private Integer numarCopii;

    @NotNull
    @NotBlank
    private Integer pretTotal;
}
