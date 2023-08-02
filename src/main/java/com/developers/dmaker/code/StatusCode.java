package com.developers.dmaker.code;

@SuppressWarnings("unused")
public enum StatusCode {
    EMPLOYED("고용"),
    RETIRED("퇴직");

    private final String description;

    StatusCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
