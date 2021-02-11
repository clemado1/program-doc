package com.mac.doc.repository;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentData, Long>, DocumentDataRepositorySupport {
    Optional<DocumentData> findFirstByDocumentOrderByDocSn(Document document);
}
