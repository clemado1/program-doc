package com.mac.doc.repository;


import com.mac.doc.dto.DocumentDto;

import java.util.Optional;

public interface DocumentRepositorySupport {
    Long updatePublishedDocSn(Long docId, Long docSn);

    Optional<DocumentDto> findDocumentWithData(Long docId, Long docSn);
}
