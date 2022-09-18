package com.opcgdb_api.constant;

import java.util.Set;

public enum LanguageCodeEnum {

    ENGLISH("en"),
    FRENCH("fr");

    private final String label;

    LanguageCodeEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public static Set<String> languagesAvailable() {
        return Set.of(ENGLISH.toString(), FRENCH.toString());
    }

}
