package com.mac.doc.dto;

import com.mac.doc.domain.Function;
import com.mac.doc.domain.type.FunctionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class FunctionDto {
    private String functionCd;
    private String functionNm;
    private FunctionType functionType;
    private Long docId;
    private Set<DocumentDto> documents;
    private String holdUserId;
    private String holdUserNm;

    public void setFunctionType(String functionType) {
        this.functionType = FunctionType.valueOf(functionType.toUpperCase());
    }

    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }

    public FunctionDto() {

    }

    @Builder
    public FunctionDto(String functionCd, String functionNm, FunctionType functionType, Set<DocumentDto> documents, String holdUserId, String holdUserNm) {
        this.functionCd = functionCd;
        this.functionNm = functionNm;
        this.functionType = functionType;
        this.documents = documents;
        this.holdUserId = holdUserId;
        this.holdUserNm = holdUserNm;
    }

    public static FunctionDto of(Function function) {
        FunctionDto functionDto = new FunctionDto();

        functionDto.functionCd = function.getFunctionCd();
        functionDto.functionNm = function.getFunctionNm();
        functionDto.functionType = function.getFunctionType();
        functionDto.holdUserId = function.getHoldUser().getUserId();
        functionDto.holdUserNm = function.getHoldUser().getUserNm();

        functionDto.documents = function.getDocuments().stream()
                .map(document -> {
                    return DocumentDto.of(document, null);
                }).collect(Collectors.toSet());

        return functionDto;
    }
}
