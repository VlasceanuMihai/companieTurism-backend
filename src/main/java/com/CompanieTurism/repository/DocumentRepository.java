package com.CompanieTurism.repository;

import com.CompanieTurism.models.Document;
import com.CompanieTurism.responses.DocumenteResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    @Query(value = "SELECT new com.CompanieTurism.responses.DocumenteResponse (a.nume, a.prenume, d.numeDocument) " +
            "FROM Document d " +
            "JOIN Angajat a ON a.id = d.angajat.id")
    List<DocumenteResponse> findAllByAngajatAndNumeDocument(Pageable pageable);
}
