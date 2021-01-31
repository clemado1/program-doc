package com.mac.doc.repository;

import com.mac.doc.domain.ProgramDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDocumentRepository extends JpaRepository<ProgramDocument, Long> {
}
