package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class TagDescriptionKey implements Serializable {

    private Long tagId;

    private String languageCode;
}
