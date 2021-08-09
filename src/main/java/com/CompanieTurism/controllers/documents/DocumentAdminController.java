package com.CompanieTurism.controllers.documents;

import com.CompanieTurism.requests.document.BaseDocumentRequest;
import com.CompanieTurism.services.document.DocumentAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentAdminController {

    private final DocumentAdminService documentAdminService;

    @Autowired
    public DocumentAdminController(DocumentAdminService documentAdminService) {
        this.documentAdminService = documentAdminService;
    }

    @GetMapping("/v1/document/{documentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getDocument(@PathVariable Integer documentId) {
        return ResponseEntity.ok(this.documentAdminService.getDocument(documentId));
    }

    @GetMapping("/v1/documents")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getDocumentsByPageable(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.documentAdminService.getDocumentsByEmployeeAndDocumentName(pageable));
    }

    @GetMapping("/v1/getAllDocuments")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllDocuments() {
        return ResponseEntity.ok(this.documentAdminService.getAllDocumentsByEmployeeAndDocumentName());
    }

    @PostMapping("/v1/createDocument")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody BaseDocumentRequest baseDocumentRequest) {
        return ResponseEntity.ok(this.documentAdminService.createDocument(baseDocumentRequest));
    }

    @PutMapping("/v1/updateDocument/{documentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateDocument(@PathVariable Integer documentId,
                                                 @Valid @RequestBody BaseDocumentRequest baseDocumentRequest) {
        return ResponseEntity.ok(this.documentAdminService.updateDocument(documentId, baseDocumentRequest));
    }

    @DeleteMapping("/v1/deleteDocument/{documentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteDocument(@PathVariable Integer documentId) {
        this.documentAdminService.deleteDocument(documentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
