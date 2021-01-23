package com.mac.doc.service;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.User;
import com.mac.doc.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    private final UserService userService;

    public DocumentServiceImpl(DocumentRepository documentRepository, UserService userService) {
        this.documentRepository = documentRepository;
        this.userService = userService;
    }

    @Override
    public Document saveDocument(Document doc) {
        return documentRepository.save(doc);
    }

    @Override
    public boolean validateWriter(String menuCd) {
        // TODO: 체크인 유저 검사
        return true;
    }

    @Override
    public boolean checkInProgram(String menuCd) {
        return false;
    }

    @Override
    public Document updateDocument(Document doc) {
        return documentRepository.save(doc);
    }

    @Override
    public Optional<Document> findOne(Long docId) {
        return documentRepository.findById(docId);
    }

    @Override
    public List<Document> findDocuments() {
        return documentRepository.findAll();
    }
}
