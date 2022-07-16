package com.opcgdb_api.entity;

import com.opcgdb_api.entity.key.ColorDescriptionKey;
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
@Table(name = "UT_COLOR_DESCRIPTION", schema = "public")
@IdClass(ColorDescriptionKey.class)
public class ColorDescriptionEntity {

    @Id
    @Column(name = "COLOR_ID", nullable = false)
    private Long colorId;

    @Id
    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(name = "name", nullable = false)
    private String name;

}
