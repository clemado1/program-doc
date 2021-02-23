package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.repository.DocumentDataRepository;
import com.mac.doc.repository.DocumentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        DocumentDto documentDto = new DocumentDto();

        documentRepository.findById(document.getDocId()).ifPresent(document1 -> {
            if (document1.getDocumentData() == null) {
                documentDataRepository.findTopByDocumentOrderByDocSnDesc(document)
                        .ifPresent(documentData -> documentDto.of(document1, documentData));
            } else {
                documentDto.of(document1, document1.getDocumentData());
            }
        });

        return documentDto;
    }

    @Override
    public DocumentDto findDocument(Document document, Long docSn) {
        DocumentDto documentDto = new DocumentDto();
        documentDataRepository.findByDocSnAndDocument(docSn, document)
                .ifPresent(documentData -> documentDto.of(documentData.getDocument(), documentData));

        return documentDto;
    }

    @Override
    public DocumentDto compareDocumentData(Document document, Long docSn) {
        return null;
    }

    @Override
    public DocumentDto compareDocumentData(Document document, Long docSn1, Long docSn2) {
        return null;
    }

    @Override
    public String diffContents(String contents1, String contents2) {
        ArrayList<String> output = new ArrayList<>();

        LinkedList<String> contentsL = contents1.lines().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> contentsR = contents2.lines().collect(Collectors.toCollection(LinkedList::new));

        char added = '+';
        char deleted = '-';

        int cursorL = 0;
        int cursorR = 0;

        boolean conflict = false;

        while (true) {
            if (contentsL.isEmpty() || contentsR.isEmpty()) {
                if (!contentsL.isEmpty()) {
                   IntStream.range(0, contentsL.size())
                            .forEach(i -> output.add(deleted + contentsL.removeFirst()));
                } else if (!contentsR.isEmpty()) {
                    IntStream.range(0, contentsR.size())
                            .forEach(i -> output.add(added + contentsR.removeFirst()));
                }
                break;
            }

            String currL = contentsL.get(cursorL);
            String currR = contentsR.get(cursorR);

            if (conflict) {
                String atL = contentsL.get(0);
                String atR = contentsR.get(0);

                if (currL.equals(atR)) {
                    // for startIdx ~ cursorL set - string
                    IntStream.range(0, cursorL)
                            .forEach(i -> output.add(deleted + contentsL.removeFirst()));
                    output.add(contentsL.removeFirst());
                    contentsR.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else if (currR.equals(atL)) {
                    // for startId ~ cursorR set + string
                    IntStream.range(0, cursorR)
                            .forEach(i -> output.add(added + contentsR.removeFirst()));
                    output.add(contentsR.removeFirst());
                    contentsL.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else if (cursorL + 1 == contentsL.size() && cursorR + 1 == contentsR.size()) {
                    IntStream.range(0, contentsL.size())
                            .forEach(i -> output.add(deleted + contentsL.removeFirst()));

                    IntStream.range(0, contentsR.size())
                            .forEach(i -> output.add(added + contentsR.removeFirst()));

                    break;
                } else {
                    cursorL++;
                    cursorR++;
                }
            } else if (currL.equals(currR)) {
                output.add(contentsL.removeFirst());
                contentsR.removeFirst();
            } else {
                conflict = true;
                cursorL = cursorL + 1 == contentsL.size() ? cursorL : cursorL + 1;
                cursorR = cursorR + 1 == contentsR.size() ? cursorR : cursorR + 1;
            }
        }

        return String.join("\n", output);
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
