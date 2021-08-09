package com.CompanieTurism.controllers.documents;

import com.CompanieTurism.services.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

//    @GetMapping("/v1/documents")
//    public ResponseEntity<Object> getDocumentsByPageable(@PageableDefault(size = 10) Pageable pageable) {
//        return ResponseEntity.ok(this.documentService.getDocumentsByEmployeeAndDocumentName(pageable));
//    }

    @GetMapping("/v1/documents")
    public ResponseEntity<Object> getDocuments() {
        return ResponseEntity.ok(this.documentService.getAllDocumentsByEmployeeAndDocumentName());
    }

}
