package com.mt.test;

public enum EmailContentType {

    TEXT("text"), HTML("html");

    private final String value;

    private EmailContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
