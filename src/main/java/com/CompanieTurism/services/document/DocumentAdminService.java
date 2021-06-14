package com.CompanieTurism.services.document;

import com.CompanieTurism.dao.DocumentDao;
import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.exceptions.DocumentNotFoundException;
import com.CompanieTurism.exceptions.EmployeeNotFoundException;
import com.CompanieTurism.exceptions.ErrorMessage;
import com.CompanieTurism.models.Document;
import com.CompanieTurism.models.Employee;
import com.CompanieTurism.repository.DocumentRepository;
import com.CompanieTurism.repository.EmployeeRepository;
import com.CompanieTurism.requests.document.BaseDocumentRequest;
import com.CompanieTurism.responses.document.BaseDocumentResponse;
import com.CompanieTurism.responses.document.DocumentResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@Slf4j
public class DocumentAdminService {

    private final DocumentService documentService;
    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;
    private final DocumentDao documentDao;

    @Autowired
    public DocumentAdminService(DocumentService documentService,
                                DocumentRepository documentRepository,
                                EmployeeRepository employeeRepository,
                                DocumentDao documentDao) {
        this.documentService = documentService;
        this.documentRepository = documentRepository;
        this.employeeRepository = employeeRepository;
        this.documentDao = documentDao;
    }

    public List<DocumentResponse> getDocumentsByEmployeeAndDocumentName(Pageable pageable) {
        return this.documentRepository.findAllByEmployeeAndDocumentName(pageable);
    }

    public List<DocumentResponse> getAllDocumentsByEmployeeAndDocumentName() {
        return this.documentRepository.findAllByEmployeeAndDocumentName();
    }

    public BaseDocumentResponse getDocument(Integer documentId) {
        Document document = this.documentRepository.findByIdAndEmployee(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(ErrorMessage.DOCUMENT_NOT_FOUND));
        log.info("Document request: {}", document);

        return BaseDocumentResponse.builder()
                .id(document.getId())
                .documentName(document.getDocumentName())
                .path(document.getPath())
                .employee(EmployeeDao.TO_EMPLOYEE_DTO.getDestination(document.getEmployee()))
                .build();
    }

    @Transactional
    @SneakyThrows
    public DocumentResponse createDocument(BaseDocumentRequest request) {
        String cnp = request.getCnp();

        Employee employee = this.employeeRepository.findByCnp(cnp)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id " + cnp + " not found!"));

        Document document = this.documentRepository.save(this.getUpdatedDocument(employee, request));

        return DocumentResponse.builder()
                .id(document.getId())
                .documentName(document.getDocumentName())
                .employeeFirstName(employee.getFirstName())
                .employeeLastName(employee.getLastName())
                .build();
    }

    private Document getUpdatedDocument(Employee employee, BaseDocumentRequest baseDocumentRequest) {
        Document document = new Document();
        document.setEmployee(employee);
        document.setDocumentName(baseDocumentRequest.getDocumentName());
        document.setPath(baseDocumentRequest.getPath());
        return document;
    }

    @Transactional
    public DocumentResponse updateDocument(Integer documentId, BaseDocumentRequest baseDocumentRequest) {
        if (!this.documentService.checkExistingId(documentId)) {
            log.info("Document with id {} not found.", documentId);
            throw new DocumentNotFoundException(ErrorMessage.DOCUMENT_NOT_FOUND);
        }

        int res = this.documentRepository.updateDocument(documentId, baseDocumentRequest.getDocumentName(), baseDocumentRequest.getPath());
        if (res < 1) {
            log.info("Cannot update document with id: {}", documentId);
            throw new PersistenceException("Cannot update document with id: " + documentId);
        }
        log.info("Document with id {} has been updated with payload {}", documentId, baseDocumentRequest);

        Document document = this.documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(ErrorMessage.DOCUMENT_NOT_FOUND));

        return DocumentResponse.builder()
                .id(document.getId())
                .documentName(document.getDocumentName())
                .employeeFirstName(document.getEmployee().getFirstName())
                .employeeLastName(document.getEmployee().getLastName())
                .build();
    }

    @Transactional
    @SneakyThrows
    public void deleteDocument(Integer documentId) {
        if (!this.documentService.checkExistingId(documentId)) {
            log.info("Document with id {} not found.", documentId);
            throw new DocumentNotFoundException("Document with id " + documentId + " not found!");
        }

        this.documentDao.delete(documentId);
        log.info("Document with id {} has been deleted!", documentId);
    }

    @Transactional
    @SneakyThrows
    public void deleteDocumentBasedOnEmployeeId(Integer employeeId) {
        List<Document> documents = this.documentRepository.findAllByEmployeeId(employeeId);
        if (documents.isEmpty()) {
            log.info("No documents for employee id {}", employeeId);
            return;
        }

        this.documentDao.deleteAll(documents);
    }
}
