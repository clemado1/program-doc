package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.repository.DocumentDataRepository;
import com.mac.doc.repository.DocumentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDataRepository documentDataRepository;
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentDataRepository documentDataRepository, DocumentRepository documentRepository) {
        this.documentDataRepository = documentDataRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    @Transactional
    public Document saveDocument(Document doc, DocumentData docData) {
        documentRepository.save(doc);
        saveDocumentData(docData);

        return doc;
    }

    @Override
    public DocumentData saveDocumentData(DocumentData docData) {
        documentDataRepository.save(docData);
        if (docData.getDocStat() == DocStat.PUBLISHED) {
            documentRepository.updatePublishedDocSn(docData.getDocument().getDocId(), docData.getDocSn());
        }

        return docData;
    }

    @Override
    @Transactional
    public boolean publishDocument(DocumentData docData) {
        Long updateCnt = 1L;
        updateCnt *= documentDataRepository.updateDocDataDocStat(docData);
        updateCnt *= documentRepository.updatePublishedDocSn(docData.getDocument().getDocId(), docData.getDocSn());

        return updateCnt > 0;
    }

    @Override
    public boolean validateWriter(Long docId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return documentRepository.findById(docId)
                .map(document -> document.getFunction().getHoldUser().getUserId().equals(userDetails.getUsername()))
                .orElse(false);
    }

    @Override
    public Document updateDocument(Document doc) {
        return documentRepository.save(doc);
    }

    @Override
    public Optional<Document> findDocument(Long docId, Optional<Long> docSn) {
        return docSn
                .map(sn -> documentRepository.findDocumentWithData(docId, sn))
                .orElse(documentRepository.findById(docId));
    }

    @Override
    public Optional<DocumentData> findDocumentData(Long docSn) {
        return documentDataRepository.findById(docSn);
    }

    @Override
    public List<Document> findDocuments() {
        return documentRepository.findAll();
    }
}
