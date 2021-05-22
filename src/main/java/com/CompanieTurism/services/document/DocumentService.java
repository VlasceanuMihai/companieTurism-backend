package com.CompanieTurism.services.document;

import com.CompanieTurism.dao.EmployeeDao;
import com.CompanieTurism.exceptions.EmployeeNotFoundException;
import com.CompanieTurism.repository.DocumentRepository;
import com.CompanieTurism.requests.document.DocumentRequest;
import com.CompanieTurism.responses.document.DocumentResponse;
import com.CompanieTurism.services.employee.EmployeeService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DocumentService {

    private final EmployeeService employeeService;
    private final DocumentRepository documentRepository;
    private final EmployeeDao employeeDao;

    @Autowired
    public DocumentService(EmployeeService employeeService, DocumentRepository documentRepository, EmployeeDao employeeDao) {
        this.employeeService = employeeService;
        this.documentRepository = documentRepository;
        this.employeeDao = employeeDao;
    }

    public boolean checkExistingId(Integer documentId){
        return this.documentRepository.existsById(documentId);
    }
}
