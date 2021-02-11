package com.mac.doc.dto;

import com.mac.doc.domain.type.ProgramType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrgForm {
    private String programCd;
    private String programNm;
    private ProgramType programType;

    public void setProgramType(String programType) {
        this.programType = ProgramType.valueOf(programType.toUpperCase());
    }
}
