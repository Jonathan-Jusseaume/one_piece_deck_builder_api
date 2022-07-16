package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.AttributeDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_ATTRIBUTE_DESCRIPTION", schema = "public")
@IdClass(AttributeDescriptionKey.class)
public class AttributeDescriptionEntity implements Serializable {

    @Id
    @Column(name = "ATTRIBUTE_ID", nullable = false)
    private Long attributeId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME", nullable = false)
    private String name;

}
