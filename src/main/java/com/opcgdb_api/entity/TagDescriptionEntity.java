package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.TagDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_TAG_DESCRIPTION", schema = "public")
@IdClass(TagDescriptionKey.class)
public class TagDescriptionEntity {

    @Id
    @Column(name = "TAG_ID", nullable = false)
    private Long tagId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME", nullable = false)
    private String name;

}
