package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.ProductDescriptionKey;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "ut_product_description", schema = "public")
@IdClass(ProductDescriptionKey.class)
public class ProductDescriptionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Id
    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(name = "name")
    private String name;

}
