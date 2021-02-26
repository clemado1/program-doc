package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.CompareDto;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.repository.DocumentDataRepository;
import com.mac.doc.repository.DocumentRepository;
import com.mac.doc.util.DocumentUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
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
    public DocumentDto findDocument(Document document) {
        return documentRepository.findById(document.getDocId())
                .map(document1 -> {
                    if (document1.getDocumentData() == null) {
                        return documentDataRepository.findTopByDocumentOrderByDocSnDesc(document)
                                .map(documentData -> DocumentDto.of(document1, documentData))
                                .orElseThrow();
                    } else {
                        return DocumentDto.of(document1, document1.getDocumentData());
                    }
                })
                .orElseThrow();
    }

    @Override
    public DocumentDto findDocument(Document document, Long docSn) {
        return documentDataRepository.findByDocSnAndDocument(docSn, document)
                .map(documentData -> DocumentDto.of(documentData.getDocument(), documentData))
                .orElseThrow();
    }

    @Override
    public CompareDto compareDocumentData(Document document, Long docSn) {
        DocumentData data1 = documentDataRepository.findById(docSn).orElseThrow();
        DocumentData data2 = documentDataRepository.findByDocumentAndDocSnLessThanOrderByDocSnDesc(document, docSn).orElseThrow();

        return retrieveCompareDto(data1, data2);
    }

    @Override
    public CompareDto compareDocumentData(Document document, Long docSn1, Long docSn2) {
        DocumentData data1 = documentDataRepository.findById(docSn1).orElseThrow();
        DocumentData data2 = documentDataRepository.findById(docSn2).orElseThrow();

        return retrieveCompareDto(data1, data2);
    }

    private CompareDto retrieveCompareDto(DocumentData data1, DocumentData data2) {
        DocumentDto docDto = DocumentDto.of(data1.getDocument(), null);
        docDto.setContents(DocumentUtil.diffContents(data1.getContents(), data2.getContents()));

        return new CompareDto(docDto, DocumentDto.of(null, data1), DocumentDto.of(null, data2));
    }

    @Override
    public Optional<DocumentData> findDocumentData(Long docSn) {
        return documentDataRepository.findById(docSn);
    }

    @Override
    public Optional<DocumentData> findFirstDocumentData(Document document) {
        return documentDataRepository.findTopByDocumentOrderByDocSnDesc(document);
    }

    @Override
    public List<Document> findDocuments() {
        return documentRepository.findAll();
    }
}
