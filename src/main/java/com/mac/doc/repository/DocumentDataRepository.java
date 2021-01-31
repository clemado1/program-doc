package com.mac.doc.repository;

import com.mac.doc.domain.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDataRepository extends JpaRepository<DocumentData, Long> {
}
