package com.CompanieTurism.services;

import com.CompanieTurism.dto.DocumentDto;
import com.CompanieTurism.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

//    public List<DocumentDto> getAllDocuments(Pageable pageable){
//        return this.documentRepository.findAll(pageable).stream()
//                .map(Do)
//    }
}
