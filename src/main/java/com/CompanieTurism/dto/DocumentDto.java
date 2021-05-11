package com.CompanieTurism.dto;

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
public class DocumentDto {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String path;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String documentName;
}
