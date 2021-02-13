package com.mac.doc.repository;

import com.mac.doc.domain.QProgram;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.dto.ProgramDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mac.doc.domain.QDocument.document;
import static com.mac.doc.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.dsl.Expressions.list;

@Repository
public class ProgramRepositorySupportImpl implements ProgramRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ProgramRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<ProgramDto> findAllPrograms() {
        QProgram p = QProgram.program;

        return queryFactory
                .from(p)
                .leftJoin(p.documents, document)
                .leftJoin(p.picUser, user)
                .transform(
                        groupBy(p.programCd).list(
                                Projections.fields(
                                        ProgramDto.class,
                                        p.programCd,
                                        p.programNm,
                                        p.programType,
                                        list(DocumentDto.class, document.docId, document.title).as("documents"))));
    }

}
