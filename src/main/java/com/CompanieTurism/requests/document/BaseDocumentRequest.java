package com.CompanieTurism.requests.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BaseDocumentRequest {

    private String cnp;

    @NotNull
    private String path;

    @NotNull
    private String documentName;
}
