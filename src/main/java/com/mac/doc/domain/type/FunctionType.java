package com.mac.doc.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FunctionType {
    USER("USER", "사용자"),
    PAY("PAY", "결제"),
    SERVICE("SERVICE", "서비스"),
    COMM("COMM", "공통");

    private static final Map<String, FunctionType> stringToEnum =
            Stream.of(values()).collect(Collectors.toMap(Objects::toString, e -> e));

    private final String functionType;
    private final String functionTypeDesc;

    FunctionType(String functionType, String functionTypeDesc) {
        this.functionType = functionType;
        this.functionTypeDesc = functionTypeDesc;
    }

    @JsonCreator
    public static FunctionType fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    @JsonValue
    public String getFunctionType() {
        return functionType;
    }
}
