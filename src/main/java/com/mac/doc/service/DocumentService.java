package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document saveDocument(Document doc, DocumentData docData);

    DocumentData saveDocumentData(DocumentData docData);

    boolean publishDocument(DocumentData docData);

    boolean validateWriter(Long docId);

    Document updateDocument(Document doc);

    Optional<Document> findDocument(Long docId, Optional<Long> docSn);

    Optional<DocumentData> findDocumentData(Long docSn);

    Optional<DocumentData> findFirstDocumentData(Document document);

    List<Document> findDocuments();
}
