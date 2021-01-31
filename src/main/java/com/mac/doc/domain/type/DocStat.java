package com.mac.doc.domain.type;

public enum DocStat {
    HOLD("10"),
    TEMPSAVE("20"),
    PUBLISHED("30"),
    RELEASE("40");

    private final String docStatCd;

    DocStat(String docStatCd) {
        this.docStatCd = docStatCd;
    }
}
