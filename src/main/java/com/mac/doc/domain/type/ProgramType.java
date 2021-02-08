package com.mac.doc.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProgramType {
    USER("USER", "사용자"),
    PAY("PAY", "결제"),
    SERVICE("SERVICE", "서비스"),
    COMM("COMM", "공통");

    private static final Map<String, ProgramType> stringToEnum =
            Stream.of(values()).collect(Collectors.toMap(Objects::toString, e -> e));

    private final String programType;
    private final String programTypeDesc;

    ProgramType(String programType, String programTypeDesc) {
        this.programType = programType;
        this.programTypeDesc = programTypeDesc;
    }

    @JsonCreator
    public static ProgramType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    @JsonValue
    public String getProgramType() {
        return programType;
    }
}
