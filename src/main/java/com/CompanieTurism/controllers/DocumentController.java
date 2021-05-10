package com.CompanieTurism.controllers;

import com.CompanieTurism.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @GetMapping("/v1/documente")
//    public ResponseEntity<Object> getAllDocuments(@PageableDefault(size = 10) Pageable pageable) {
//        return ResponseEntity.ok(this.documentService.);
//    }
}
