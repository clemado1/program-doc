package com.mac.doc.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.mac.doc.domain.QDocument.document;

public class DocumentRepositorySupportImpl implements DocumentRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DocumentRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long updatePublishedDocSn(Long docId, Long docSn) {
        return queryFactory.update(document)
                .where(document.docId.eq(docId))
                .set(document.docSn, docSn)
                .execute();
    }
}
