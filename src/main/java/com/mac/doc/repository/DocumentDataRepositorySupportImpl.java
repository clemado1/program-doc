package com.mac.doc.repository;

import com.mac.doc.domain.DocumentData;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.transaction.annotation.Transactional;

import static com.mac.doc.domain.QDocumentData.documentData;

public class DocumentDataRepositorySupportImpl implements DocumentDataRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DocumentDataRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    @Transactional
    public Long updateDocDataDocId(DocumentData targetData, Long docId) {
        return queryFactory.update(documentData)
                .where(documentData.docSn.eq(targetData.getDocSn()))
                .set(documentData.document.docId, docId)
                .execute();
    }
}
