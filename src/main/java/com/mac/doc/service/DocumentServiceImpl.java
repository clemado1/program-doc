package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.repository.DocumentDataRepository;
import com.mac.doc.repository.DocumentRepository;
import com.mac.doc.util.DocumentUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        LinkedList<String> output = new LinkedList<>();

        LinkedList<String> contentsL = contents1.lines().collect(Collectors.toCollection(LinkedList::new));
        LinkedList<String> contentsR = contents2.lines().collect(Collectors.toCollection(LinkedList::new));

        char pls = '+';
        char mns = '-';

        int cursorL = 0;
        int cursorR = 0;

        boolean conflict = false;

        while (true) {
            if (isSourceEmpty(output, contentsL, contentsR)) break;

            if (cursorL == contentsL.size() && cursorR == contentsR.size()) {
                output.addAll(contentsL.stream().map(s -> mns + s).collect(Collectors.toList()));
                output.addAll(contentsR.stream().map(s -> pls + s).collect(Collectors.toList()));

                break;
            }

            String currL = contentsL.get(cursorL);
            String currR = contentsR.get(cursorR);

            if (conflict) {
                String atL = contentsL.getFirst();
                String atR = contentsR.getFirst();

                if (DocumentUtil.equalsWithLength(currL, atR)) {
                    // for startIdx ~ cursorL set - string
                    acceptTimes(output, contentsL, cursorL, mns);
                    contentsR.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else if (DocumentUtil.equalsWithLength(currR, atL)) {
                    // for startId ~ cursorR set + string
                    acceptTimes(output, contentsR, cursorR, pls);
                    contentsL.removeFirst();

                    cursorL = 0;
                    cursorR = 0;
                    conflict = false;
                } else {
                    cursorL++;
                    cursorR++;
                }
            } else if (DocumentUtil.equalsWithLength(currL, currR)) {
                output.add(contentsL.removeFirst());
                contentsR.removeFirst();
            } else {
                conflict = true;
                cursorL = Math.min(cursorL + 1, contentsL.size());
                cursorR = Math.min(cursorR + 1, contentsR.size());
            }
        }

        return String.join("\n", output);
    }

    private boolean isSourceEmpty(List<String> output, List<String> l, List<String> r) {
        if (l.isEmpty() || r.isEmpty()) {
            if (!l.isEmpty()) {
                output.addAll(l.stream().map(s -> DocumentUtil.mns + s).collect(Collectors.toList()));
            } else if (!r.isEmpty()) {
                output.addAll(r.stream().map(s -> DocumentUtil.pls + s).collect(Collectors.toList()));
            }
            return true;
        }
        return false;
    }

    private void acceptTimes(Deque<String> i, Deque<String> d, int times, char prefix) {
        for (int t = 0; t < times; t++) {
            i.add(prefix + d.removeFirst());
        }
        i.add(d.removeFirst());
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
