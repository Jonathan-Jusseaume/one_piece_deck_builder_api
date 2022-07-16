package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CardColorKey implements Serializable {

    private Long colorId;

    private String cardId;
}
