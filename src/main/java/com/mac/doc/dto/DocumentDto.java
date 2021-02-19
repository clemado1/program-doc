package com.mac.doc.dto;

import com.mac.doc.domain.Document;
import com.mac.doc.domain.DocumentData;
import com.mac.doc.domain.type.DocStat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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

    public void setDocStat(String docStat) {
        this.docStat = DocStat.valueOf(docStat.toUpperCase());
    }
    public void setDocStat(DocStat docStat) {
        this.docStat = docStat;
    }

    public DocumentDto() {}

    @Builder
    public DocumentDto(Long docId, Long docSn, String title, DocStat docStat, String contents, Double version, String functionCd, String functionNm, String holdUserId, String holdUserNm) {
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
    }

    public DocumentDto of(Optional<Document> document, Optional<DocumentData> documentData) {
        DocumentDto documentDto = new DocumentDto();
        document.ifPresent(document1 -> {
            documentDto.setDocId(document1.getDocId());
            documentDto.setTitle(document1.getTitle());
            documentDto.setFunctionCd(document1.getFunction().getFunctionCd());
            documentDto.setFunctionNm(document1.getFunction().getFunctionNm());
            documentDto.setHoldUserId(document1.getFunction().getHoldUser().getUserId());
            documentDto.setHoldUserNm(document1.getFunction().getHoldUser().getUserNm());

        });

        documentData.ifPresent(documentData1 -> {
            documentDto.setDocId(documentData1.getDocument().getDocId());
            documentDto.setDocSn(documentData1.getDocSn());
            documentDto.setDocStat(documentData1.getDocStat());
            documentDto.setContents(documentData1.getContents());
            documentDto.setVersion(documentData1.getVersion());
        });

        return documentDto;
    }
}
