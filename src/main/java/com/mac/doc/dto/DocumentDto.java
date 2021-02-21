package com.mac.doc.dto;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDto {
    private Long docId;
    private Long docSn;
    private String title;
    private DocStat docStat;
    private String contents;
    private Double version;
    private String functionCd;
    private String functionNm;
    private String holdUserId;
    private String holdUserNm;
    private String rgsnUserId;
    private String rgsnUserNm;
    private String modiUserId;
    private String modiUserNm;

    public void setDocStat(String docStat) {
        this.docStat = DocStat.valueOf(docStat.toUpperCase());
    }
    public void setDocStat(DocStat docStat) {
        this.docStat = docStat;
    }

    public DocumentDto() {}

    @Builder
    public DocumentDto(Long docId, Long docSn, String title, DocStat docStat, String contents, Double version, String functionCd, String functionNm, String holdUserId, String holdUserNm, String rgsnUserId, String rgsnUserNm, String modiUserId, String modiUserNm) {
        this.docId = docId;
        this.docSn = docSn;
        this.title = title;
        this.docStat = docStat;
        this.contents = contents;
        this.version = version;
        this.functionCd = functionCd;
        this.functionNm = functionNm;
        this.holdUserId = holdUserId;
        this.holdUserNm = holdUserNm;
        this.rgsnUserId = rgsnUserId;
        this.rgsnUserNm = rgsnUserNm;
        this.modiUserId = modiUserId;
        this.modiUserNm = modiUserNm;
    }

    public void of(Document document, DocumentData documentData) {
        if (document != null) {
            this.docId = document.getDocId();
            this.title = document.getTitle();
            this.functionCd = document.getFunction().getFunctionCd();
            this.functionNm = document.getFunction().getFunctionNm();
            if (document.getFunction().getHoldUser() != null) {
                this.holdUserId = document.getFunction().getHoldUser().getUserId();
                this.holdUserNm = document.getFunction().getHoldUser().getUserNm();
            }
        }
        if (documentData != null) {
            this.docId = documentData.getDocument().getDocId();
            this.docSn = documentData.getDocSn();
            this.docStat = documentData.getDocStat();
            this.contents = documentData.getContents();
            this.version = documentData.getVersion();
        }
    }
}
