package com.CompanieTurism.controllers.documents;

import com.CompanieTurism.requests.document.DocumentRequest;
import com.CompanieTurism.services.document.DocumentAdminService;
import com.CompanieTurism.services.document.DocumentService;
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
public class DocumentAdminController {

    private final DocumentAdminService documentAdminService;
    private final DocumentService documentService;

    @Autowired
    public DocumentAdminController(DocumentAdminService documentAdminService, DocumentService documentService) {
        this.documentAdminService = documentAdminService;
        this.documentService = documentService;
    }

    @GetMapping("/v1/documents")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllDocuments(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(this.documentAdminService.getDocumentsByEmployeeAndDocumentName(pageable));
    }

    @PostMapping("/v1/createDocument")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> createDocument(@Valid @RequestBody DocumentRequest documentRequest) {
        return ResponseEntity.ok(this.documentAdminService.createDocument(documentRequest));
    }

    @PutMapping("/v1/updateDocument/{documentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateDocument(@PathVariable Integer documentId,
                                                 @Valid @RequestBody DocumentRequest documentRequest) {
        return ResponseEntity.ok(this.documentAdminService.updateDocument(documentId, documentRequest));
    }

    @DeleteMapping("/v1/deleteDocument/{documentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteDocument(@PathVariable Integer documentId) {
        this.documentAdminService.deleteDocument(documentId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
