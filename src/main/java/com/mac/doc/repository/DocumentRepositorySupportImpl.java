package com.mac.doc.repository;

import com.mac.doc.domain.Document;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Optional;

import static com.mac.doc.domain.QDocument.document;
import static com.mac.doc.domain.QDocumentData.documentData;

public class DocumentRepositorySupportImpl implements DocumentRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DocumentRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Long updatePublishedDocSn(Long docId, Long docSn) {
        return queryFactory.update(document)
                .where(document.docId.eq(docId))
                .set(document.documentData.docSn, docSn)
                .execute();
    }

    @Override
    public Optional<Document> findDocumentWithData(Long docId, Long docSn) {
        return Optional.ofNullable(queryFactory.selectFrom(document)
                .leftJoin(document.documentData, documentData)
                .where(document.docId.eq(docId), documentData.docSn.eq(docSn))
                .fetchOne());
    }
}
