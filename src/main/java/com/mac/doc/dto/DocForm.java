package com.mac.doc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocForm {
    private String docId;
    private String docSn;
    private String title;
    private String contents;
    private Double version;
    private String programCd;
}
