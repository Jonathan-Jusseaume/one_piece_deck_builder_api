package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class TypeDescriptionKey implements Serializable {

    private Long typeId;

    private String languageCode;
}
