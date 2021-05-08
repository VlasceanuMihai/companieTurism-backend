package com.CompanieTurism.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
@JGlobalMap
public class HotelDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String nume;

    @NotNull
    @NotBlank
    @Max(1)
    private Integer stele;
}
