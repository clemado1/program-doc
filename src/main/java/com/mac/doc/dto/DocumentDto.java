package com.mac.doc.dto;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    public DocumentDto() {
    }

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

    public static DocumentDto of(Document document, DocumentData documentData) {
        DocumentDto documentDto = new DocumentDto();

        if (document != null) {
            documentDto.docId = document.getDocId();
            documentDto.title = document.getTitle();
            documentDto.functionCd = document.getFunction().getFunctionCd();
            documentDto.functionNm = document.getFunction().getFunctionNm();
            if (document.getFunction().getHoldUser() != null) {
                documentDto.holdUserId = document.getFunction().getHoldUser().getUserId();
                documentDto.holdUserNm = document.getFunction().getHoldUser().getUserNm();
            }
        }
        if (documentData != null) {
            documentDto.docId = documentData.getDocument().getDocId();
            documentDto.docSn = documentData.getDocSn();
            documentDto.docStat = documentData.getDocStat();
            documentDto.contents = documentData.getContents();
            documentDto.version = documentData.getVersion();
        }

        return documentDto;
    }

    @Getter
    public static class DocData {
        private Long docId;
        private Long docSn;
        private Double version;
        private DocStat docStat;
        private String rgsnUserNm;
        private String modiUserNm;
        private String rgsnDttm;
        private String modiDttm;

        public DocData() {
        }

        @Builder
        public DocData(DocumentData documentData) {
            this.docId = documentData.getDocument().getDocId();
            this.docSn = documentData.getDocSn();
            this.version = documentData.getVersion();
            this.docStat = documentData.getDocStat();
            this.rgsnUserNm = documentData.getRgsnUser().getUserNm();
            this.modiUserNm = documentData.getModiUser().getUserNm();

            DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("MMM dd yy''");

            this.rgsnDttm = documentData.getRgsnDttm().format(ofPattern);
            this.modiDttm = documentData.getModiDttm().format(ofPattern);
        }
    }

    public void setDocStat(String docStat) {
        this.docStat = DocStat.valueOf(docStat.toUpperCase());
    }

    public void setDocStat(DocStat docStat) {
        this.docStat = docStat;
    }
}
