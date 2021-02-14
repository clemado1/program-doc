package com.mac.doc.dto;

import com.mac.doc.domain.type.ProgramType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProgramDto {
    private String programCd;
    private String programNm;
    private ProgramType programType;
    private Long docId;
    private Set<DocumentDto> documents;
    private String picUserId;
    private String picUserNm;

    public ProgramDto(String programCd, String programNm, ProgramType programType, Set<DocumentDto> documents, String picUserId, String picUserNm) {
        this.programCd = programCd;
        this.programNm = programNm;
        this.programType = programType;
        this.documents = documents;
        this.picUserId = picUserId;
        this.picUserNm = picUserNm;
    }

    public void setProgramType(String programType) {
        this.programType = ProgramType.valueOf(programType.toUpperCase());
    }
}
