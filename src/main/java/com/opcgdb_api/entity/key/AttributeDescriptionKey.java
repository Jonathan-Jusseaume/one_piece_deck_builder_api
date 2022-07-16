package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class AttributeDescriptionKey implements Serializable {

    private Long attributeId;

    private String languageCode;
}
