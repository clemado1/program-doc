package com.mac.doc.repository;

import static com.mac.doc.domain.QProgram.program;
import static com.mac.doc.domain.QDocument.document;

import com.mac.doc.domain.Program;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramRepositorySupportImpl implements ProgramRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public ProgramRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Program> findAllPrograms() {
        return queryFactory
                .selectFrom(program)
                .leftJoin(program.document, document)
                .fetchJoin()
                .distinct()
                .fetch();
    }

}
