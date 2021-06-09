package com.CompanieTurism.repository;

import com.CompanieTurism.models.Document;
import com.CompanieTurism.responses.document.DocumentResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Query(value = "SELECT new com.CompanieTurism.responses.document.DocumentResponse(d.id, e.lastName, e.firstName, d.documentName) " +
            "FROM Document d " +
            "JOIN Employee e ON e.id = d.employee.id")
    List<DocumentResponse> findByEmployeeAndDocumentName(Pageable pageable);

    @Query(value = "SELECT new com.CompanieTurism.responses.document.DocumentResponse(d.id, e.lastName, e.firstName, d.documentName) " +
            "FROM Document d " +
            "JOIN Employee e ON e.id = d.employee.id")
    List<DocumentResponse> findAllByPageableBasedOnEmployeeAndDocumentName();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Document " +
            "SET documentName = :documentName, path = :path " +
            "WHERE id = :documentId")
    int updateDocument(@Param("documentId") Integer documentId,
                       @Param("documentName") String documentName,
                       @Param("path") String path);
}
