package com.opcgdb_api.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_ATTRIBUTE", schema = "public")
public class AttributeEntity implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID")
    private Set<AttributeDescriptionEntity> descriptions;

}
