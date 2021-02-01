package com.mac.doc.repository;

import com.mac.doc.domain.DocumentData;

public interface DocumentDataRepositorySupport {
    Long updateDocDataDocId(DocumentData targetData, Long docId);
}
