package com.mac.doc.repository;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.Program;
import com.mac.doc.domain.QProgram;
import com.mac.doc.dto.DocumentDto;
import com.mac.doc.dto.ProgramDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mac.doc.domain.QProgram.program;
import static com.mac.doc.domain.QDocument.document;
import static com.mac.doc.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;

@Repository
public class ProgramRepositorySupportImpl implements ProgramRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ProgramRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<ProgramDto> findAllPrograms() {
        return queryFactory
                .select(
                        Projections.constructor(
                                ProgramDto.class,
                                program.programCd,
                                program.programNm,
                                program.programType,
                                set(Projections.fields(DocumentDto.class, document.docId, document.title)),
                                user.userId.as("picUserId"),
                                user.userNm.as("picUserNm")))
                .from(program)
                .leftJoin(program.documents, document)
                .fetchJoin()
                .leftJoin(program.picUser, user)
                .fetchJoin()
                .distinct()
                .fetch();
    }

}
