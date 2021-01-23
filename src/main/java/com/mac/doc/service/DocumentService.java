package com.mac.doc.service;

import com.mac.doc.domain.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document saveDocument(Document doc);

    boolean validateWriter(String menuCd);

    boolean checkInProgram(String menuCd);

    Document updateDocument(Document doc);

    Optional<Document> findOne(Long docId);

    List<Document> findDocuments();
}
