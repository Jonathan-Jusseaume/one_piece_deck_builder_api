package com.opcgdb_api.entity.key;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ProductDescriptionKey implements Serializable {

    private String productId;

    private String languageCode;
}
