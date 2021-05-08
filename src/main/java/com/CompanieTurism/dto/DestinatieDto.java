package com.CompanieTurism.dto;

import com.CompanieTurism.enums.ScenariuCovid;
import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class DestinatieDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String tara;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String oras;

    @NotNull
    @NotBlank
    private ScenariuCovid scenariuCovid;
}
