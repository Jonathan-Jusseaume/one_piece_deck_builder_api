package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.TypeDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_TYPE_DESCRIPTION", schema = "public")
@IdClass(TypeDescriptionKey.class)
public class TypeDescriptionEntity {

    @Id
    @Column(name = "TYPE_ID", nullable = false)
    private Long typeId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME", nullable = false)
    private String name;

}
