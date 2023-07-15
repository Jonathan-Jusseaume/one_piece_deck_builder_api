package com.opcgdb_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_PRODUCT", schema = "public")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Set<ProductDescriptionEntity> descriptions;

}
