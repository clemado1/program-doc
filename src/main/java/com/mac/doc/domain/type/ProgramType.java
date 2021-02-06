package com.mac.doc.domain.type;

public enum ProgramType {
    USER("USER", "사용자"),
    PAY("PAY", "결제"),
    SERVICE("SERVICE", "서비스"),
    COMM("COMM", "공통");

    private final String programType;
    private final String programTypeDesc;

    ProgramType(String programType, String programTypeDesc) {
        this.programType = programType;
        this.programTypeDesc = programTypeDesc;
    }
}
