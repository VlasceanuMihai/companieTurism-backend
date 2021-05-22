package com.CompanieTurism.dao;

import com.CompanieTurism.dto.DocumentDto;
import com.CompanieTurism.models.Document;
import com.CompanieTurism.repository.DocumentRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DocumentDao {

    public static final JMapper<DocumentDto, Document> TO_DOCUMENT_DTO = new JMapper<>(DocumentDto.class, Document.class);
    public static final JMapper<Document, DocumentDto> TO_DOCUMENT_ENTITY = new JMapper<>(Document.class, DocumentDto.class);

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentDao(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional(readOnly = true)
    public Optional<DocumentDto> findById(Integer id) {
        return this.documentRepository.findById(id).map(TO_DOCUMENT_DTO::getDestination);
    }

    @Transactional
    public DocumentDto save(DocumentDto documentDto) {
        Document document = this.documentRepository.save(TO_DOCUMENT_ENTITY.getDestination(documentDto));
        return TO_DOCUMENT_DTO.getDestination(document);
    }

    @Transactional
    public void delete(Integer documentId) {
        this.documentRepository.deleteById(documentId);
    }
}
