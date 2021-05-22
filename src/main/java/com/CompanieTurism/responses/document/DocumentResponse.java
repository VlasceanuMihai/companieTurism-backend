package com.CompanieTurism.responses.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DocumentResponse {

    private String employeeFirstName;

    private String employeeLastName;

    private String documentName;
}
