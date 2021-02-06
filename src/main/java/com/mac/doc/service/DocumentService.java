package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document saveDocument(Document doc);

    DocumentData saveDocumentData(DocumentData docData);

    Document publishDocument(DocumentData docData);

    boolean validateWriter(String programCd);

    boolean checkInProgram(String programCd);

    Document updateDocument(Document doc);

    Optional<Document> findOne(Long docId);

    List<Document> findDocuments();
}
