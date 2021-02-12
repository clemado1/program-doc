package com.mac.doc.repository;


import com.mac.doc.domain.Document;

import java.util.Optional;

public interface DocumentRepositorySupport {
    Long updatePublishedDocSn(Long docId, Long docSn);

    Optional<Document> findDocumentWithData(Long docId, Long docSn);
}
