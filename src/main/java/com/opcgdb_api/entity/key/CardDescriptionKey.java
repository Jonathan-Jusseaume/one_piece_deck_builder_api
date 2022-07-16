package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CardDescriptionKey implements Serializable {

    private String cardId;

    private String languageCode;
}
