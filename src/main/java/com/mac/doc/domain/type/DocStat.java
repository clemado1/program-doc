package com.mac.doc.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DocStat {
    HOLD("10"),
    TEMPSAVE("20"),
    PUBLISHED("30"),
    RELEASE("40");

    private static final Map<String, DocStat> stringToEnum =
            Stream.of(values()).collect(Collectors.toMap(Objects::toString, e -> e));

    private final String docStatCd;

    DocStat(String docStatCd) {
        this.docStatCd = docStatCd;
    }

    @JsonCreator
    public static DocStat fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    @JsonValue
    public String getDocStatCd() {
        return docStatCd;
    }
}
