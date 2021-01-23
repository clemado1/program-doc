package com.mac.doc.domain.type;

public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
