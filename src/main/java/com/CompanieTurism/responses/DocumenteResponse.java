package com.CompanieTurism.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DocumenteResponse {

    private String numeAngajat;

    private String prenumeAngajat;

    private String numeDocument;
}
