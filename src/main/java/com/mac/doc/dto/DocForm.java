package com.mac.doc.dto;

import com.mac.doc.domain.type.DocStat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocForm {
    private String docId;
    private String docSn;
    private String title;
    private DocStat docStat;
    private String contents;
    private Double version;
    private String programCd;

    public void setDocStat(String docStat) {
        this.docStat = DocStat.valueOf(docStat.toUpperCase());
    }
}
