package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.CompetitiveStatusDescriptionKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "UT_COMPETITIVE_STATUS_DESCRIPTION", schema = "public")
@IdClass(CompetitiveStatusDescriptionKey.class)
public class CompetitiveStatusDescriptionEntity {

    @Id
    @Column(name = "STATUS_ID", nullable = false)
    private Long statusId;

    @Id
    @Column(name = "LANGUAGE_CODE", nullable = false)
    private String languageCode;

    @Column(name = "NAME", nullable = false)
    private String name;

}
