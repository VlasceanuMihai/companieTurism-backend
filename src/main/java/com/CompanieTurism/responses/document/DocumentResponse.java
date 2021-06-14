package com.CompanieTurism.responses.document;

import com.CompanieTurism.responses.employee.EmployeeProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DocumentResponse {

    private Integer id;

    private String employeeFirstName;

    private String employeeLastName;

    private String documentName;

//    private EmployeeProfileResponse employee;
}
