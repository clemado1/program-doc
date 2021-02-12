package com.mac.doc.repository;

import com.mac.doc.domain.DocumentData;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.mac.doc.domain.QDocumentData.documentData;

public class DocumentDataRepositorySupportImpl implements DocumentDataRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DocumentDataRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long updateDocDataDocStat(DocumentData targetData) {
        return queryFactory.update(documentData)
                .where(documentData.docSn.eq(targetData.getDocSn()))
                .set(documentData.docStat, targetData.getDocStat())
                .execute();
    }
}
