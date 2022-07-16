package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.ProductDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_PRODUCT_DESCRIPTION", schema = "public")
@IdClass(ProductDescriptionKey.class)
public class ProductDescriptionEntity {

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME")
    private String name;

}
