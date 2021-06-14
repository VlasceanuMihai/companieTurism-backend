package com.CompanieTurism.responses.document;

import com.CompanieTurism.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BaseDocumentResponse {

    private Integer id;

    private String documentName;

    private String path;

    private EmployeeDto employee;
}
