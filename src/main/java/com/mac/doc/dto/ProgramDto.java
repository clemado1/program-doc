package com.mac.doc.dto;

import com.mac.doc.domain.type.ProgramType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProgramDto {
    private String programCd;
    private String programNm;
    private ProgramType programType;
    private Long docId;
    private List<DocumentDto> documents;
    private String picUserId;
    private String picUserNm;

    public void setProgramType(String programType) {
        this.programType = ProgramType.valueOf(programType.toUpperCase());
    }
}
