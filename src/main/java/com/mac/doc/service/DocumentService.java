package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.dto.CompareDto;
import com.mac.doc.dto.DocumentDto;

import java.util.List;
import java.util.Optional;

public interface DocumentService {
    Document saveDocument(Document doc, DocumentData docData);

    DocumentData saveDocumentData(DocumentData docData);

    boolean publishDocument(DocumentData docData);

    Document updateDocument(Document doc);

    DocumentDto findDocument(Document document);

    DocumentDto findDocument(Document document, Long docSn);

    CompareDto compareDocumentData(Document document, Long docSn);

    CompareDto compareDocumentData(Document document, Long docSn1, Long docSn2);

    Optional<DocumentData> findDocumentData(Long docSn);

    Optional<DocumentData> findFirstDocumentData(Document document);

    List<Document> findDocuments();
}
