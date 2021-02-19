package com.mac.doc.repository;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Function;
import com.mac.doc.dto.DocumentDto;
import com.querydsl.core.types.Projections;
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
    public Optional<DocumentDto> findDocumentWithData(Long docId, Long docSn) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.fields(
                                DocumentDto.class,
                                document.docId,
                                document.function.functionCd,
                                document.function.functionNm,
                                document.function.functionType,
                                document.title,
                                documentData.docSn,
                                documentData.docStat,
                                documentData.contents,
                                documentData.version,
                                document.rgsnDttm,
                                document.modiDttm
                        ))
                        .from(document)
                        .join(documentData).on(document.docId.eq(documentData.document.docId))
                        .join(document.function)
                        .where(document.docId.eq(docId), documentData.docSn.eq(docSn))
                        .fetchOne());
    }
}
