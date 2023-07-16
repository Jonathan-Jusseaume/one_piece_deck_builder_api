package com.opcgdb_api.repository.specification;

public enum SpecificationKeyEnum {

    CARD_DESCRIPTION("descriptions"),
    COLOR("colors"),
    COMPETITIVE_STATUS("competitiveStatus"),
    COST("cost"),
    DECK_DESCRIPTION("description"),
    EFFECT("effect"),
    ID("id"),

    IMAGE("images"),
    LEADER("leader"),
    MAIL("mail"),
    NAME("name"),
    POWER("power"),
    PRODUCT("product"),
    RARITY("rarity"),
    TAG("tags"),
    TYPE("type"),
    USER("user"),
    USERS_FAVORITE("usersFavorite");

    private final String key;

    SpecificationKeyEnum(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
