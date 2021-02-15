package com.mac.doc.dto;

import com.mac.doc.domain.type.FunctionType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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

    public FunctionDto() {
    }

    public FunctionDto(String functionCd, String functionNm, FunctionType functionType, Set<DocumentDto> documents, String holdUserId, String holdUserNm) {
        this.functionCd = functionCd;
        this.functionNm = functionNm;
        this.functionType = functionType;
        this.documents = documents;
        this.holdUserId = holdUserId;
        this.holdUserNm = holdUserNm;
    }

    public void setFunctionType(String functionType) {
        this.functionType = FunctionType.valueOf(functionType.toUpperCase());
    }
}
