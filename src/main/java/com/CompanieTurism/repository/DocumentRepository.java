package com.CompanieTurism.repository;

import com.CompanieTurism.models.Document;
import com.CompanieTurism.responses.DocumentsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Query(value = "SELECT new com.CompanieTurism.responses.DocumentsResponse(e.lastName, e.firstName, d.documentName) " +
            "FROM Document d " +
            "JOIN Employee e ON e.id = d.employee.id")
    List<DocumentsResponse> findAllByEmployeeAndDocumentName(Pageable pageable);
}
