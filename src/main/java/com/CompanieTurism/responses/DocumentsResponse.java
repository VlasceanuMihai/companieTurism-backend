package com.CompanieTurism.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DocumentsResponse {

    private String employeeLastName;

    private String employeeFirstName;

    private String documentName;

//    public DocumentsResponse(String employeeLastName, String employeeFirstName, String documentName) {
//        this.employeeLastName = employeeLastName;
//        this.employeeFirstName = employeeFirstName;
//        this.documentName = documentName;
//    }
}
