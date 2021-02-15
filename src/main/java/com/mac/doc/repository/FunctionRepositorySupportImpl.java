package com.mac.doc.repository;

import com.mac.doc.dto.DocumentDto;
import com.mac.doc.dto.FunctionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mac.doc.domain.QDocument.document;
import static com.mac.doc.domain.QFunction.function;
import static com.mac.doc.domain.QUser.user;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;

@Repository
public class FunctionRepositorySupportImpl implements FunctionRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public FunctionRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<FunctionDto> findAllFunctions() {
        return queryFactory
                .from(function)
                .leftJoin(function.documents, document)
                .leftJoin(function.holdUser, user)
                .transform(groupBy(function).list(
                        Projections.constructor(
                                FunctionDto.class,
                                function.functionCd,
                                function.functionNm,
                                function.functionType,
                                set(Projections.fields(DocumentDto.class, document.docId, document.title)),
                                user.userId.as("holdUserId"),
                                user.userNm.as("holdUserNm"))
                ));
    }

}
